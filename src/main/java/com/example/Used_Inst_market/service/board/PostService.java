package com.example.Used_Inst_market.service.board;

import com.example.Used_Inst_market.model.domain.board.post.Post;
import com.example.Used_Inst_market.model.domain.board.post.PostRepository;
import com.example.Used_Inst_market.model.domain.board.post.PostSpecification;
import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.brand.BrandRepository;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategoryRepository;
import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.local.lower.LowerLocalRepository;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocalRepository;
import com.example.Used_Inst_market.model.domain.search.Search;
import com.example.Used_Inst_market.model.domain.search.SearchRepository;
import com.example.Used_Inst_market.model.domain.user.User;
import com.example.Used_Inst_market.model.domain.user.UserRepository;
import com.example.Used_Inst_market.model.dto.board.post.PostInsertDTO;
import com.example.Used_Inst_market.model.dto.board.post.PostUpdateDTO;
import com.example.Used_Inst_market.model.dto.board.search.PostSearchSelectDTO;
import com.example.Used_Inst_market.model.vo.board.PostVO;
import com.example.Used_Inst_market.model.vo.category.BrandVO;
import com.example.Used_Inst_market.model.vo.category.LowerCategoryVO;
import com.example.Used_Inst_market.model.vo.category.UpperCategoryVO;
import com.example.Used_Inst_market.model.vo.local.LowerLocalVO;
import com.example.Used_Inst_market.model.vo.local.UpperLocalVO;
import com.example.Used_Inst_market.util.enums.SoldYN;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final SearchRepository searchRepository;
    private final UpperCategoryRepository upperCategoryRepository;
    private final LowerCategoryRepository lowerCategoryRepository;
    private final BrandRepository brandRepository;
    private final UpperLocalRepository upperLocalRepository;
    private final LowerLocalRepository lowerLocalRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<PostVO> selectAll() {
        return postRepository.findAll().stream()
                .map(PostVO::new)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public PostVO select(Long postNo) throws IOException {
        final Post post = postRepository.findById(postNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        return PostVO.from(post);
    }

    @Transactional
    public Long insert(PostInsertDTO postInsertDTO) {
        final User user = userRepository.findById(postInsertDTO.getUserNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        final UpperCategory upperCategory = upperCategoryRepository
                .findById(postInsertDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        final LowerCategory lowerCategory = lowerCategoryRepository
                .findById(postInsertDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        final Brand brand = brandRepository.findById(postInsertDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브렌드가 없습니다."));

        final UpperLocal upperLocal = upperLocalRepository
                .findById(postInsertDTO.getUpperLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        final LowerLocal lowerLocal = lowerLocalRepository
                .findById(postInsertDTO.getLowerLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        Post post = postRepository.save(
                Post.builder()
                        .user(user)
                        .title(postInsertDTO.getTitle())
                        .content(postInsertDTO.getContent())
                        .price(postInsertDTO.getPrice())
                        .build());

        searchRepository.save(
                Search.builder()
                        .post(post)
                        .upperCategory(upperCategory)
                        .lowerCategory(lowerCategory)
                        .brand(brand)
                        .upperLocal(upperLocal)
                        .lowerLocal(lowerLocal)
                        .build());

        return post.getPostNo();
    }

    @Transactional
    public Long update(PostUpdateDTO postUpdateDTO) {
        final Post post = postRepository.findById(postUpdateDTO.getPostNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        final Search search = searchRepository.findByPost(post)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        final UpperCategory upperCategory = upperCategoryRepository
                .findById(postUpdateDTO.getUpperCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        final LowerCategory lowerCategory = lowerCategoryRepository
                .findById(postUpdateDTO.getLowerCategoryNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        final Brand brand = brandRepository.findById(postUpdateDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브렌드가 없습니다."));

        final UpperLocal upperLocal = upperLocalRepository
                .findById(postUpdateDTO.getUpperLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        final LowerLocal lowerLocal = lowerLocalRepository
                .findById(postUpdateDTO.getLowerLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));

        post.update(postUpdateDTO.getTitle(), postUpdateDTO.getContent(), postUpdateDTO.getPrice(), postUpdateDTO.getSoldYN());
        search.update(upperCategory, lowerCategory, brand, upperLocal, lowerLocal);

        return postUpdateDTO.getPostNo();
    }

    @Transactional
    public Long updateSoldYN(Long postNo) {
        final Post post = postRepository.findById(postNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        if(post.getSoldYN() == SoldYN.SALE) {
            post.updateSoldYN(SoldYN.SOLD_OUT);
        } else {
            post.updateSoldYN(SoldYN.SALE);
        }

        return postNo;
    }

    @Transactional
    public void delete(Long postNo) {
        final Post post = postRepository.findById(postNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public List<PostVO> selectFromSearchingKey(PostSearchSelectDTO postSearchSelectDTO) {
        Map<String, Object> searchKey = new HashMap<>();

        if (postSearchSelectDTO.getUpperCategoryNo() != null) {
            final UpperCategory upperCategory = upperCategoryRepository.findById(postSearchSelectDTO.getUpperCategoryNo())
                    .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));
            searchKey.put("upperCategory", upperCategory);
        }
        if (postSearchSelectDTO.getLowerCategoryNo() != null) {
            final LowerCategory lowerCategory = lowerCategoryRepository.findById(postSearchSelectDTO.getLowerCategoryNo())
                    .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));
            searchKey.put("lowerCategory", lowerCategory);
        }
        if (postSearchSelectDTO.getBrandNo() != null) {
            final Brand brand = brandRepository.findById(postSearchSelectDTO.getBrandNo())
                    .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));
            searchKey.put("brand", brand);
        }
        if (postSearchSelectDTO.getUpperLocalNo() != null) {
            final UpperLocal upperLocal = upperLocalRepository.findById(postSearchSelectDTO.getUpperLocalNo())
                    .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));
            searchKey.put("upperLocal", upperLocal);
        }
        if (postSearchSelectDTO.getLowerLocalNo() != null) {
            final LowerLocal lowerLocal = lowerLocalRepository.findById(postSearchSelectDTO.getLowerLocalNo())
                    .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다."));
            searchKey.put("lowerLocal", lowerLocal);
        }
        if (postSearchSelectDTO.getMinPrice() != null) {
            searchKey.put("minPrice", postSearchSelectDTO.getMinPrice());
        }
        if (postSearchSelectDTO.getMaxPrice() != null) {
            searchKey.put("maxPrice", postSearchSelectDTO.getMaxPrice());
        }
        if (postSearchSelectDTO.getKeyword() != null) {
            searchKey.put(postSearchSelectDTO.getKeywordType(), postSearchSelectDTO.getKeyword());
        }

        return postRepository.findAll(PostSpecification.postSearch(searchKey))
                .stream().map(PostVO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UpperCategoryVO upperCategorySelectFromPost(Long postNo) {
        final UpperCategory upperCategory = searchSelectFromPost(postNo).getUpperCategory();

        return new UpperCategoryVO(upperCategory);
    }

    @Transactional(readOnly = true)
    public LowerCategoryVO lowerCategorySelectFromPost(Long postNo) {
        final LowerCategory lowerCategory = searchSelectFromPost(postNo).getLowerCategory();

        return LowerCategoryVO.from(lowerCategory);
    }

    @Transactional(readOnly = true)
    public BrandVO brandSelectFromPost(Long postNo) {
        final Brand brand = searchSelectFromPost(postNo).getBrand();

        return BrandVO.from(brand);
    }

    @Transactional(readOnly = true)
    public UpperLocalVO upperLocalSelectFromPost(Long postNo) {
        final UpperLocal upperLocal = searchSelectFromPost(postNo).getUpperLocal();

        return UpperLocalVO.from(upperLocal);
    }

    @Transactional(readOnly = true)
    public LowerLocalVO lowerLocalSelectFromPost(Long postNo) {
        final LowerLocal lowerLocal = searchSelectFromPost(postNo).getLowerLocal();

        return LowerLocalVO.from(lowerLocal);
    }

    private Search searchSelectFromPost(Long postNo) {
        final Post post = postRepository.findById(postNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        return searchRepository.findByPost(post)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));
    }
}