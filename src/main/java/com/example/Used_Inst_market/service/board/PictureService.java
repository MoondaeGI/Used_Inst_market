package com.example.Used_Inst_market.service.board;

import com.example.Used_Inst_market.model.domain.board.picture.Picture;
import com.example.Used_Inst_market.model.domain.board.picture.PictureRepository;
import com.example.Used_Inst_market.model.domain.board.post.Post;
import com.example.Used_Inst_market.model.domain.board.post.PostRepository;
import com.example.Used_Inst_market.model.vo.board.PictureVO;
import com.example.Used_Inst_market.util.filehandler.FileHandler;
import com.example.Used_Inst_market.web.dto.board.picture.PictureInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.board.picture.PictureSelectByPostRequestDTO;
import com.example.Used_Inst_market.web.dto.board.picture.PictureSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.board.picture.PictureUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PictureService {
    private final FileHandler fileHandler;
    private final PictureRepository pictureRepository;
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PictureVO select(PictureSelectRequestDTO pictureSelectRequestDTO)
            throws IOException {
        Picture picture = pictureRepository
                .findById(pictureSelectRequestDTO.getPictureNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 없습니다."));
        byte[] imageByteArrays = fileHandler.imageToByteArray(picture);

        return PictureVO.of(picture, imageByteArrays);
    }

    @Transactional(readOnly = true)
    public List<PictureVO> selectByPost(
            PictureSelectByPostRequestDTO pictureSelectByPostRequestDTO) throws IOException {
        List<PictureVO> selectResult = new ArrayList<>();

        Post post = postRepository.findById(pictureSelectByPostRequestDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        List<Picture> pictures = pictureRepository.findByPost(post);

        for(Picture picture : pictures) {
            byte[] imageToByteArray = fileHandler.imageToByteArray(picture);
            selectResult.add(PictureVO.of(picture, imageToByteArray));
        }

        return selectResult;
    }

    @Transactional
    public Long insert(PictureInsertRequestDTO pictureInsertRequestDTO) throws IOException {
        Post post = postRepository.findById(pictureInsertRequestDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        List<MultipartFile> multipartFiles = pictureInsertRequestDTO.getMultipartFiles();

        if(!multipartFiles.isEmpty()) {
            List<Picture> pictures = fileHandler
                    .parsePictureFileInfo(post, multipartFiles);
            pictureRepository.saveAll(pictures);
        }

        return pictureInsertRequestDTO.getPostNo();
    }

    @Transactional
    public void update(PictureUpdateRequestDTO pictureUpdateRequestDTO) throws IOException {
        List<MultipartFile> multipartFiles = pictureUpdateRequestDTO.getMultipartFiles();
        List<String> multipartFileNames = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            multipartFileNames.add(multipartFile.getOriginalFilename());
        }

        List<MultipartFile> updateFileList = new ArrayList<>();
        List<Picture> deleteFileList = new ArrayList<>();

        Post post = postRepository.findById(pictureUpdateRequestDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        List<Picture> pictures = pictureRepository.findByPost(post);

        if(CollectionUtils.isEmpty(pictures)) {
            updateFileList.addAll(multipartFiles);
        } else {
            List<String> originalFileNameList = new ArrayList<>();

            for(Picture picture : pictures) {
                String originalFileName = picture.getOriginalFileName();
                if(!multipartFileNames.contains(originalFileName)) {
                    deleteFileList.add(picture);
                } else {
                    originalFileNameList.add(originalFileName);
                }
            }

            for(MultipartFile multipartFile : multipartFiles) {
                String originalFileName = multipartFile.getOriginalFilename();
                if(!originalFileNameList.contains(originalFileName)) {
                    updateFileList.add(multipartFile);
                }
            }
        }

        fileHandler.deleteImageFile(deleteFileList);
        List<Picture> updatePictures = pictureRepository.saveAll(
                fileHandler.parsePictureFileInfo(post, updateFileList));

        pictureRepository.saveAll(updatePictures);
        pictureRepository.deleteAll(deleteFileList);
    }
}
