//package pre14.stackoverflow.util;
//
//import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
//import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
//
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
//
//public interface ApiDocumentUtils {
//    static OperationRequestPreprocessor getRequestPreProcessor() {
//        return preprocessRequest(prettyPrint());
//    }
//
//    static OperationResponsePreprocessor getResponsePreProcessor() {
//        return preprocessResponse(prettyPrint());
//    }
//}
//
