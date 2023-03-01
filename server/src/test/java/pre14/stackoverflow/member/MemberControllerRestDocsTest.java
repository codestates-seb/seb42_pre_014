//package pre14.stackoverflow.member;
//
//import com.google.gson.Gson;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.constraints.ConstraintDescriptions;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import pre14.stackoverflow.answer.mapper.AnswerMapper;
//import pre14.stackoverflow.member.controller.MemberController;
//import pre14.stackoverflow.member.dto.MemberDto;
//import pre14.stackoverflow.member.entity.Member;
//import pre14.stackoverflow.member.mapper.MemberMapper;
//import pre14.stackoverflow.member.service.MemberService;
//import pre14.stackoverflow.questions.mapper.QuestionMapper;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
//import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.restdocs.snippet.Attributes.key;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static pre14.stackoverflow.util.ApiDocumentUtils.getRequestPreProcessor;
//import static pre14.stackoverflow.util.ApiDocumentUtils.getResponsePreProcessor;
//
//@WebMvcTest(
//        controllers = MemberController.class,
//        excludeAutoConfiguration = SecurityAutoConfiguration.class, // 추가
//        excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}
//)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//class MemberControllerRestDocsTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean // 1
//    private MemberService memberService; // 5 controller 클래스가 의존하는 객체를 Mock Bean 객체로 주입
//
//    @MockBean // 2
//    private MemberMapper memberMapper;
//
//    @MockBean
//    private QuestionMapper questionMapper;
//
//    @MockBean
//    private AnswerMapper answerMapper;
//
//    @Autowired
//    private Gson gson;
//
//    @Test
//    public void postMemberTest() throws Exception {
//        // 3 given
//        MemberDto.Post memberPostDto = new MemberDto.Post("ksj@gmail.com","김석진","010-1234-5678","qwert12345");
//        String memberPostBody = gson.toJson(memberPostDto);
//
//        // 4
//        MemberDto.Response memberResponse = new MemberDto.Response(
//                1L,
//                "ksj@gamil.com",
//                "김석진",
//                "010-1234-5678",
//                List.of("USER_ROLE"),
//                Member.MemberStatus.MEMBER_ACTIVE);
//
//        // 5
//        given(memberMapper.memberPostToMember(Mockito.any(MemberDto.Post.class))).willReturn(new Member());
//
//        // 6
//        given(memberService.createMember(Mockito.any(Member.class))).willReturn(new Member());
//
//        // 7
//        given(memberMapper.memberToMemberResponse(Mockito.any(Member.class))).willReturn(memberResponse);
//
//        // when
//        // 8 request 전송
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/members")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(memberPostBody)
//                );
//        System.out.println(actions.andReturn().getResponse().getContentAsString());
//
//        ConstraintDescriptions postMemberConstraintDescriptions = new ConstraintDescriptions(MemberDto.Post.class);
//        List<String> emailDescriptions = postMemberConstraintDescriptions.descriptionsForProperty("email");
//        List<String> nameDescriptions = postMemberConstraintDescriptions.descriptionsForProperty("name");
//        List<String> phoneDescriptions= postMemberConstraintDescriptions.descriptionsForProperty("phone");
//        List<String> passwordDescriptions = postMemberConstraintDescriptions.descriptionsForProperty("password");
//
//
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.email").value(memberResponse.getEmail()))
//                .andExpect(jsonPath("$.name").value(memberResponse.getName()))
//                .andExpect(jsonPath("$.roles").isArray())
//                .andExpect(jsonPath("$.phone").value(memberResponse.getPhone()))
//                .andDo(document("post-member",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일")
//                                                .attributes(key("constraints").value(emailDescriptions)),
//                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
//                                                .attributes(key("constraints").value(nameDescriptions)),
//                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("핸드폰")
//                                                .attributes(key("constraints").value(phoneDescriptions)),
//                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
//                                                .attributes(key("constraints").value(passwordDescriptions))
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
//                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
//                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("핸드폰"),
//                                        fieldWithPath("status").type(JsonFieldType.STRING)
//                                                .description("회원 상태: ACTIVE(로그인) /  QUIT(로그아웃)"),
//                                        fieldWithPath("roles").type(JsonFieldType.ARRAY)
//                                                .description("권한: ROLE_USER(유저) / ROLE_ADMIN(관리자)")
//                                )
//                        )
//                ));
//    }
//
//    @Test
//    void patchMemberTest() throws Exception {
//        //given
//        long memberId = 1L;
//        MemberDto.Patch memberPatch = new MemberDto.Patch(1L,
//                "김석진",
//                "010-1234-5678",
//                "qwert12345",
//                Member.MemberStatus.MEMBER_ACTIVE);
//
//        String requestBody = gson.toJson(memberPatch);
//
//        MemberDto.Response memberResponse = new MemberDto.Response(
//                1L,
//                "김석진",
//                "ksj@gamil.com",
//                "010-1234-5678",
//                List.of("USER_ROLE"),
//                Member.MemberStatus.MEMBER_ACTIVE);
//
//        given(memberMapper.memberPatchToMember(Mockito.any(MemberDto.Patch.class))).willReturn(new Member());
//        given(memberService.updateMember(Mockito.any(Member.class))).willReturn(new Member());
//        given(memberMapper.memberToMemberResponse(Mockito.any(Member.class))).willReturn(memberResponse);
//
//        //when
//        ResultActions actions = mockMvc.perform(
//                MockMvcRequestBuilders.patch("/members/{member-id}", memberId)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody)
//        );
//
//        ConstraintDescriptions patchConstraintDescriptions = new ConstraintDescriptions(MemberDto.Patch.class);
//        List<String> passwordDescriptions = patchConstraintDescriptions.descriptionsForProperty("password");
//        List<String> nameDescriptions = patchConstraintDescriptions.descriptionsForProperty("name");
//        List<String> statusDescriptions = patchConstraintDescriptions.descriptionsForProperty("status");
//
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(memberPatch.getMemberId()))
//                .andExpect(jsonPath("$.name").value(memberPatch.getName()))
//                .andExpect(jsonPath("$.phone").value(memberPatch.getPhone()))
//                .andExpect(jsonPath("$.status").value(memberPatch.getMemberStatus()))
//                .andExpect(jsonPath("$.roles").isArray())
//                .andDo(document("patch-member",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                Arrays.asList(parameterWithName("member-id").description("회원 식별자"))
//                        ),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("회원 식별자").ignored(),
//                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
//                                                .attributes(key("constrains").value(nameDescriptions)).optional(),
//                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("핸드폰 번호"),
//                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀 번호")
//                                                .attributes(key("constraints").value(passwordDescriptions)).optional(),
//                                        fieldWithPath("status").type(JsonFieldType.STRING)
//                                                .description("회원 상태: ACTIVE(활동중) / QUIT(로그아웃) ").optional()
//                                )
//                        ),
//                        responseFields(
//                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
//                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
//                                fieldWithPath("phone").type(JsonFieldType.STRING).description("핸드폰 번호"),
//                                fieldWithPath("status").type(JsonFieldType.STRING)
//                                        .description("회원 상태: ACTIVE(활동중) /  QUIT(로그아웃)"),
//                                fieldWithPath("roles").type(JsonFieldType.ARRAY)
//                                        .description("권한: ROLE_USER(유저) / ROLE_ADMIN(관리자)")
//                        )
//                ));
//    }
//
//    @Test
//    public void getMemberTest() throws Exception {
//        long memberId = 1L;
//        MemberDto.Response memberResponseDto = new MemberDto.Response(memberId,
//                "김석진",
//                "ksj@gmail.com",
//                "010-1234-5678.",
//                List.of("ROLE_USER"),
//                Member.MemberStatus.MEMBER_ACTIVE);
//
//        given(memberService.findMember(Mockito.anyLong())).willReturn(new Member());
//        given(memberMapper.memberToMemberResponse(Mockito.any(Member.class))).willReturn(memberResponseDto);
//
//        ResultActions resultActions = mockMvc.perform(
//                get("/members/{member-id}", memberId)
//                        .accept(MediaType.APPLICATION_JSON)
//        );
//
//        resultActions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.memberId").value(memberId))
//                .andExpect(jsonPath("$.name").value(memberResponseDto.getName()))
//                .andExpect(jsonPath("$.email").value(memberResponseDto.getEmail()))
//                .andExpect(jsonPath("$.phone").value(memberResponseDto.getPhone()))
//                .andExpect(jsonPath("$.status").value(memberResponseDto.getMemberStatus()))
//                .andExpect(jsonPath("$.roles").isArray())
//                .andDo(
//                        document("get-member",
//                                getRequestPreProcessor(),
//                                getResponsePreProcessor(),
//                                pathParameters(
//                                        Arrays.asList(parameterWithName("member-id").description("회원 식별자"))
//                                ),
//                                responseFields(
//                                        Arrays.asList(
//                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
//                                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
//                                                fieldWithPath("phone").type(JsonFieldType.STRING).description("핸드폰 번호"),
//                                                fieldWithPath("status").type(JsonFieldType.STRING)
//                                                        .description("회원 상태: ACTIVE(활동중) / QUIT(로그아웃) "),
//                                                fieldWithPath("roles").type(JsonFieldType.ARRAY)
//                                                        .description("권한: ROLE_USER(유저) / ROLE_ADMIN(관리자)")
//                                        )
//                                )
//                        )
//                );
//    }
//
//    @Test
//    public void deleteMember() throws Exception {
//        long memberId = 1L;
//        doNothing().when(memberService).deleteMember(Mockito.anyLong());
//
//        ResultActions resultActions = mockMvc.perform(
//                delete("/members/{member-id}", memberId)
//        );
//
//        resultActions
//                .andExpect(status().isNoContent())
//                .andDo(document(
//                        "delete-member",
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                Arrays.asList(parameterWithName("member-id").description("회원 식별자"))
//                        )
//                ));
//    }
//
//}