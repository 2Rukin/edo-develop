package model.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    public static final String ADDRESS_URL = "api/repository/address";
    public static final String APPEAL_URL = "api/repository/appeal";
    public static final String AUTHOR_URL = "api/repository/author";
    public static final String DEPARTMENT_URL = "api/repository/department";
    public static final String EMPLOYEE_URL = "api/repository/employee";
    public static final String FILEPOOL_URL = "api/repository/filePool";
    public static final String NOMENCLATURE_URL = "api/repository/nomenclature";
    public static final String QUESTION_URL = "api/repository/question";
    public static final String RESOLUTION_URL = "api/repository/resolution";
    public static final String THEME_URL = "api/repository/theme";

    public static final String EDO_REPOSITORY_NAME = "edo-repository";
    public static final String EDO_SERVICE_NAME = "edo-service";
    public static final String EMPLOYEE_FIO_SEARCH_PARAMETER = "fullName";
    public static final String EDO_FILE_STORAGE_NAME = "edo-file-storage";
    public static final String NOTIFICATION_URL = "api/repository/notification";

    public static final String REST_TO_SERVICE_APPROVAL_QUEUE = "RestToServiceApprovalQueue";
}
