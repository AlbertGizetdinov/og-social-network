package ru.itis.og.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.request.ReactionRequest;
import ru.itis.og.dto.response.ReactionResponse;
import ru.itis.og.dto.response.page.ReactionPageResponse;
import ru.itis.og.exception.AccountNotFoundException;
import ru.itis.og.exception.OgServiceException;
import ru.itis.og.exception.PostNotFoundException;
import ru.itis.og.exception.ReactionNotFoundException;
import ru.itis.og.model.Post;
import ru.itis.og.model.Reaction;
import ru.itis.og.model.enumeration.State;
import ru.itis.og.repository.AccountRepository;
import ru.itis.og.repository.PostRepository;
import ru.itis.og.repository.ReactionRepository;
import ru.itis.og.service.ReactionService;

import java.util.Optional;
import java.util.UUID;

import static ru.itis.og.dto.response.ReactionResponse.from;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {

    private final AccountRepository accountRepository;

    private final PostRepository postRepository;

    private final ReactionRepository reactionRepository;

    @Override
    public ReactionPageResponse getReactions(IdPageRequest idPageRequest) {
        PageRequest pageRequest = PageRequest.of(idPageRequest.getPage(), idPageRequest.getSize(), Sort.by("createDate"));
        Page<Reaction> reactionPage = reactionRepository.findAllByPost_Id(UUID.fromString(idPageRequest.getId()), pageRequest);
        return ReactionPageResponse.builder()
                .reactions(from(reactionPage.getContent()))
                .totalPages(reactionPage.getTotalPages())
                .build();
    }

    @Override
    public ReactionResponse createReaction(ReactionRequest reactionRequest) {
        Post post = postRepository.findById(UUID.fromString(reactionRequest.getPostId()))
                .orElseThrow(PostNotFoundException::new);
        Optional<Reaction> optionalReaction = reactionRepository.findByPost_IdAndAccount_Id(
                UUID.fromString(reactionRequest.getPostId()), UUID.fromString(reactionRequest.getAccountId()));
        if (post.getState() != State.DELETED && !optionalReaction.isPresent()) {
            Reaction reaction = Reaction.builder()
                    .account(accountRepository.findById(UUID.fromString(reactionRequest.getAccountId()))
                            .orElseThrow(AccountNotFoundException::new))
                    .post(post)
                    .reaction(reactionRequest.getReactionType())
                    .build();
            return from(reactionRepository.save(reaction));
        }
        throw new OgServiceException(HttpStatus.BAD_REQUEST, "This post is deleted");
    }

    @Override
    public void deleteReaction(IdPageRequest idPageRequest) {
        Reaction reaction = reactionRepository.findById(UUID.fromString(idPageRequest.getId()))
                .orElseThrow(ReactionNotFoundException::new);
        reactionRepository.delete(reaction);
    }
}
