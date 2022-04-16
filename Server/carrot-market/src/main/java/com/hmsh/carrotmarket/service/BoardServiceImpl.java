package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.converter.BoardConverter;
import com.hmsh.carrotmarket.converter.BoardReplyConverter;
import com.hmsh.carrotmarket.converter.ImageConverter;
import com.hmsh.carrotmarket.converter.ProductConverter;
import com.hmsh.carrotmarket.dto.*;
import com.hmsh.carrotmarket.entity.*;
import com.hmsh.carrotmarket.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final LikesRepository likesRepository;

    private final BoardRepository boardRepository;

    private final BoardImageRepository boardImageRepository;

    private final ReplyLikesRepository replyLikesRepository;

    private final BoardReplyImageRepository boardReplyImageRepository;

    private final BoardReplyRepository boardReplyRepository;

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

    /**
     * 게시글 삭제
     * @param id 삭제할 게시글의 ID
     */
    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (!optionalBoard.isPresent()) throw new IllegalArgumentException();

        Board board = optionalBoard.get();
        boardImageRepository.deleteAllByBoard(board);
        boardReplyRepository.deleteAllByBoard(board);
        boardRepository.delete(board);
    }

    /**
     * 게시글 댓글 등록
     * @param boardReplyDTO 댓글 정보
     * @return 등록된 댓글의 ID
     */
    @Override
    public Long registerReply(BoardReplyDTO boardReplyDTO, MultipartFile[] uploadFiles) {
        BoardReply reply = BoardReplyConverter.replyDTOToReply(boardReplyDTO);

        if (!Objects.isNull(uploadFiles)) {
            List<ImageDTO> imageDTOList = fileService.uploadImageFiles(uploadFiles);
            List<BoardReplyImage> boardImageList = imageDTOList.stream()
                    .map(imageDTO -> ImageConverter.imageDTOToBoardReplyImage(imageDTO, reply))
                    .collect(Collectors.toList());

            log.info("save images = {}", imageDTOList);
            boardReplyImageRepository.saveAll(boardImageList);
        }

        BoardReply save = boardReplyRepository.save(reply);
        return save.getId();
    }

    /**
     * 해당 ID를 가진 게시글의 댓글 조회
     * @param id 조회할 댓글 ID
     * @return ID를 가진 게시글 댓글 정보
     */
    @Override
    public BoardReplyDTO getReply(Long id) {
        Optional<BoardReply> optionalBoardReply = boardReplyRepository.findById(id);
        if (!optionalBoardReply.isPresent()) throw new IllegalArgumentException();

        BoardReply boardReply = optionalBoardReply.get();
        List<String> imagePathList = boardReplyImageRepository.findAllByBoardReply(boardReply).stream()
                .map(ImageConverter::imageToImageDTO)
                .map(ImageDTO::getImageURL)
                .collect(Collectors.toList());
        return BoardReplyConverter.replyToReplyDTO(boardReply, imagePathList);
    }

    /**
     * 게시글의 댓글 리스트 조회
     * @param id Board(게시글) ID
     * @return 게시글의 댓글 리스트
     */
    @Override
    public List<BoardReplyListDTO> getReplyList(Long id) {
        List<BoardReply> replyList = boardReplyRepository.findAllByBoard(Board.builder().id(id).build());
        List<BoardReplyListDTO> boardReplyListDTO = new ArrayList<>();

        for(BoardReply boardReply:replyList){
            List<String> imagePathList = boardReplyImageRepository.findAllByBoardReply(boardReply).stream()
                    .map(ImageConverter::imageToImageDTO)
                    .map(ImageDTO::getImageURL)
                    .collect(Collectors.toList());
            boardReplyListDTO.add(BoardReplyConverter.replyToReplyListDTO(boardReply, imagePathList));
        }

        return boardReplyListDTO;
    }

    /**
     * 게시글의 댓글 수정
     * @param boardReplyDTO 수정한 댓글 정보
     */
    @Override
    public void modifyReply(BoardReplyDTO boardReplyDTO) {
        Optional<BoardReply> optionalBoardReply = boardReplyRepository.findById(boardReplyDTO.getId());
        if (!optionalBoardReply.isPresent()) throw new IllegalArgumentException();

        BoardReply boardReply = optionalBoardReply.get();
        boardReply.setContent(boardReplyDTO.getContent());

        boardReplyRepository.save(boardReply);
    }

    /**
     * 게시글의 댓글 삭제
     * @param id 삭제할 댓글의 id
     */
    @Override
    public void deleteReply(Long id) {
        Optional<BoardReply> optionalBoardReply = boardReplyRepository.findById(id);
        if (!optionalBoardReply.isPresent()) throw new IllegalArgumentException();

        boardReplyRepository.delete(optionalBoardReply.get());
    }


    /**
     * 좋아요 누른 댓글 리스트 조회
     * @param phoneNumber 로그인한 사용자 ID
     * @return 좋아요 누른 댓글 리스트
     */
    @Override
    public List<BoardReplyDTO> getLikesReplyList(String phoneNumber) {
        List<BoardReplyDTO> boardReplyDTOList =boardReplyRepository.getBoardReplyByReplyLikes(Member.builder().phoneNumber(phoneNumber).build()).stream()
                .map(BoardReplyConverter::replyToLikeReplyDTO)
                .collect(Collectors.toList());

        if (boardReplyDTOList.isEmpty())
            throw new IllegalArgumentException("좋아요를 누른 댓글이 없음");

        return boardReplyDTOList;
    }

    /**
     * 댓글 좋아요 등록
     * @param likesDTO 좋아요 등록 멤버와 댓글 정보
     * @return 좋아요 등록 댓글 ID
     */
    @Override
    public long registReplyLike(LikesDTO likesDTO) {
        ReplyLikes replyLikes = ReplyLikes.builder()
                .member(Member.builder().phoneNumber(likesDTO.getPhoneNumber()).build())
                .boardReply(BoardReply.builder().id(likesDTO.getBoardReplyId()).build())
                .board(Board.builder().id(likesDTO.getBoardId()).build())
                .build();

        ReplyLikes save = replyLikesRepository.save(replyLikes);

        Optional<BoardReply> optionalBoardReply = boardReplyRepository.findById(likesDTO.getBoardReplyId());
        if (!optionalBoardReply.isPresent()) throw new IllegalArgumentException();

        BoardReply boardReply = optionalBoardReply.get();
        boardReply.setLikes(boardReply.getLikes() + 1);
        boardReplyRepository.save(boardReply);

        return save.getBoardReply().getId();
    }

    /**
     * 댓글 좋아요 취소
     * @param likesDTO 댓글 좋아요 등록 멤버와 댓글 정보
     */
    @Override
    public void removeReplyLikes(LikesDTO likesDTO) {
        replyLikesRepository.deleteReplyLikesByMemberAndBoardReply(
                Member.builder().phoneNumber(likesDTO.getPhoneNumber()).build(),
                BoardReply.builder().id(likesDTO.getBoardReplyId()).build(),
                Board.builder().id(likesDTO.getBoardId()).build()
        );

        Optional<BoardReply> optionalBoardReply = boardReplyRepository.findById(likesDTO.getBoardReplyId());
        if (!optionalBoardReply.isPresent()) throw new IllegalArgumentException();

        BoardReply boardReply = optionalBoardReply.get();
        boardReply.setLikes(boardReply.getLikes() - 1);
        boardReplyRepository.save(boardReply);
    }
}
