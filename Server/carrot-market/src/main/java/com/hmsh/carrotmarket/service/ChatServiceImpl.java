package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.ChatDTO;
import com.hmsh.carrotmarket.dto.ChatListDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService{

    @Value("${upload_path}")
    private String uploadPath;

    @Override
    public boolean sendMessage(ChatDTO chatDTO) {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();

        log.info("chatDTO{}", chatDTO);

        if (Objects.isNull(chatDTO.getMessage())){
            return false;
        }
        else{
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter( uploadPath + File.separator + chatDTO.getTitle() + chatDTO.getProductId(), true));
                PrintWriter pw = new PrintWriter(bw, true);
                pw.write("[" + chatDTO.getName() + "]" + "\n" + chatDTO.getMessage() + " " + hour + ":" + minute + "\n");
                pw.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public ChatListDTO getMessage(ChatDTO chatDTO) {
        log.info("chatDTO : {}", chatDTO);
        log.info("chatPath : {}", uploadPath + File.separator + chatDTO.getTitle() + chatDTO.getProductId());
        try {
            byte[] message = Files.readAllBytes(Paths.get(uploadPath + File.separator + chatDTO.getTitle() + chatDTO.getProductId()));
                ChatListDTO chatListDTO = ChatListDTO.builder()
                        .chatId(chatDTO.getTitle() + chatDTO.getProductId())
                        .message(new String(message))
                        .build();
                return chatListDTO;
        } catch (NoSuchFileException e) {
            ChatListDTO chatListDTO = ChatListDTO.builder()
                    .chatId(chatDTO.getTitle() + chatDTO.getProductId())
                    .message("대화를 시작합니다")
                    .build();
            return chatListDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
