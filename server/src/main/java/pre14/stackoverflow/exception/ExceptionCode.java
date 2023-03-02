package pre14.stackoverflow.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    QUESTION_NOT_FOUND(404,"Question not found"),
    ANSWER_NOT_FOUND(404,"Answer not found"),
    MEMBER_EXISTS(409, "Member exists"),
    QUESTION_EXISTS(409, "Question exists"),
    QUESTION_CANNOT_CHANGE(403,"권한이 없습니다."),
    VOTE_NOT_FOUND(404, "Vote not found"),
    TOKEN_NOT_FOUND(404, "token not found"),
    NOT_AUTHORIZED(404, "not authorized"),
    PASSWORD_NOT_CONFIRMED(404, "Password not confirmed"),
    MEMBER_ALREADY_VOTED(405, "아직 투표를 하지 않았습니다."),
    ANSWER_CANNOT_CHANGE(403,"권한이 없습니다.");

    @Getter
    private final int status;


    @Getter
    private final String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
