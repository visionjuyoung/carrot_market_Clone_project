package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.converter.BoardConverter;
import com.hmsh.carrotmarket.converter.ImageConverter;
import com.hmsh.carrotmarket.dto.BoardDTO;
import com.hmsh.carrotmarket.dto.ImageDTO;
import com.hmsh.carrotmarket.entity.Board;
import com.hmsh.carrotmarket.entity.BoardImage;
import com.hmsh.carrotmarket.repository.BoardImageRepository;
import com.hmsh.carrotmarket.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final BoardImageRepository boardImageRepository;

    private final FileService fileService;


    /**
     * 게시글 등록
     * @param boardDTO 게시글 정보
     * @param files 업로드된 이미지 파일들
     * @return 등록된 게시글의 ID
     */
    @Override
    @Transactional
    public Long register(BoardDTO boardDTO, MultipartFile[] files) {
        Board board = BoardConverter.dtoToBoard(boardDTO);

        if (!Objects.isNull(files)) {
            List<ImageDTO> imageDTOList = fileService.uploadImageFiles(files);
            List<BoardImage> boardImageList = imageDTOList.stream()
                    .map(imageDTO -> ImageConverter.imageDTOToBoardImage(imageDTO, board))
                    .collect(Collectors.toList());

            log.info("save images = {}", imageDTOList);
            boardImageRepository.saveAll(boardImageList);
        }

        Board saved = boardRepository.save(board);
        return saved.getId();
    }

    /**
     * 게시글 조회
     * @param id 조회할 게시글의 ID
     * @return 게시글 정보
     */
    @Override
    public BoardDTO get(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (!optionalBoard.isPresent()) throw new IllegalArgumentException();

        Board board = optionalBoard.get();
        List<String> imagePathList = boardImageRepository.findAllByBoard(board).stream()
                .map(ImageConverter::imageToImageDTO)
                .map(ImageDTO::getImageURL)
                .collect(Collectors.toList());

        return BoardConverter.boardToBoardDTO(board, imagePathList);
    }

    /**
     * 게시글 수정
     * @param boardDTO 수정된 게시글 정보
     * @param files 수정된 이미지 파일들
     */
    @Override
    @Transactional
    public void modify(BoardDTO boardDTO, MultipartFile[] files) {
        Optional<Board> optionalBoard = boardRepository.findById(boardDTO.getId());
        if (!optionalBoard.isPresent()) throw new IllegalArgumentException();

        Board board = optionalBoard.get();
        board.modifyInfo(boardDTO.getContent(), boardDTO.getBoardCategory());
        boardRepository.save(board);
        boardImageRepository.deleteAllByBoard(board);

        List<ImageDTO> imageDTOList = fileService.uploadImageFiles(files);
        List<BoardImage> imageList = imageDTOList.stream()
                .map(dto -> ImageConverter.imageDTOToBoardImage(dto, board))
                .collect(Collectors.toList());
        boardImageRepository.saveAll(imageList);
    }
}
