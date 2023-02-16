package pre14.stackoverflow.member.dto;

public class MemberPatchDto {

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getMemberId() {
        return memberId;
    }

    private long memberId;

    private String name;
    private String email;
    private String phone;
}
