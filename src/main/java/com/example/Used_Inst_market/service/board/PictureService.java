package com.example.Used_Inst_market.service.board;

import com.example.Used_Inst_market.model.domain.board.picture.Picture;
import com.example.Used_Inst_market.model.domain.board.picture.PictureRepository;
import com.example.Used_Inst_market.model.domain.board.post.Post;
import com.example.Used_Inst_market.model.domain.board.post.PostRepository;
import com.example.Used_Inst_market.model.dto.board.picture.*;
import com.example.Used_Inst_market.model.vo.board.PictureVO;
import com.example.Used_Inst_market.util.filehandler.FileHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PictureService {
    private final FileHandler fileHandler;
    private final PictureRepository pictureRepository;
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PictureVO select(final PictureSelectDTO pictureSelectDTO) throws IOException {
        final Picture picture = pictureRepository
                .findById(pictureSelectDTO.getPictureNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 없습니다."));
        final byte[] imageByteArrays = fileHandler.imageToByteArray(picture);

        return PictureVO.of(picture, imageByteArrays);
    }

    @Transactional(readOnly = true)
    public List<PictureVO> selectByPost(final PictureSelectByPostDTO pictureSelectByPostDTO)
            throws IOException {
        List<PictureVO> selectResult = new ArrayList<>();

        final Post post = postRepository.findById(pictureSelectByPostDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        List<Picture> pictures = pictureRepository.findByPost(post);

        for(Picture picture : pictures) {
            byte[] imageToByteArray = fileHandler.imageToByteArray(picture);
            selectResult.add(PictureVO.of(picture, imageToByteArray));
        }

        return selectResult;
    }

    @Transactional
    public Long insert(final PictureInsertDTO pictureInsertDTO) throws IOException {
        final Post post = postRepository.findById(pictureInsertDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        List<MultipartFile> multipartFiles = pictureInsertDTO.getMultipartFiles();

        if(!multipartFiles.isEmpty()) {
            final List<Picture> pictures = fileHandler
                    .parseImageFileInfo(post, multipartFiles);
            pictureRepository.saveAll(pictures);
        }

        return pictureInsertDTO.getPostNo();
    }

    @Transactional
    public void update(final PictureUpdateDTO pictureUpdateDTO) throws IOException {
        List<MultipartFile> multipartFiles = pictureUpdateDTO.getMultipartFiles();
        List<String> multipartFileNames = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            multipartFileNames.add(multipartFile.getOriginalFilename());
        }

        List<MultipartFile> updateFileList = new ArrayList<>();
        List<Picture> deleteFileList = new ArrayList<>();

        final Post post = postRepository.findById(pictureUpdateDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        final List<Picture> pictures = pictureRepository.findByPost(post);

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
                fileHandler.parseImageFileInfo(post, updateFileList));

        pictureRepository.saveAll(updatePictures);
        pictureRepository.deleteAll(deleteFileList);
    }

    @Transactional
    public void delete(final PictureDeleteDTO pictureDeleteDTO) throws IOException {
        final Post post = postRepository.findById(pictureDeleteDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        List<Picture> pictures = pictureRepository.findByPost(post);
        fileHandler.deleteImageFile(pictures);
    }
}
