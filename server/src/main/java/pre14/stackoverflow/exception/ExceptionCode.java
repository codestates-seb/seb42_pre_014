package pre14.stackoverflow.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),


    QUESTION_NOT_FOUND(404,"Question not found"),
    QUESTION_EXISTS(409, "Question exists"),
    QUESTION_CANNOT_CHANGE(403,"Question Can Not Be Changed"),

    ANSWER_NOT_FOUND(404,"Answer not found"),
    ANSWER_CANNOT_CHANGE(403,"권한이 없습니다."),
    MEMBER_ALREADY_VOTED(405, "아직 투표를 하지 않았습니다."),
    TAG_NOT_FOUND(404, "Tag not found" ),
    TAG_EXISTS(409, "Tag exists" );

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
