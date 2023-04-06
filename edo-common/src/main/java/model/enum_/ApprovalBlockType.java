package model.enum_;

/**
 * Enum "MemberType" - представляет собой тип блока листа согласования
 */
public enum ApprovalBlockType {
    PARTICIPANT_BLOCK("Блок с участниками"),
    SIGNATORY_BLOCK("Блок с подписантами");

    /**
     * Поле для хранения значения константы
     */
    private final String approvalBlockType;

    /**
     * Конструктор для передачи значения константы в поле для хранения
     */
    ApprovalBlockType(String approvalBlockType) {
        this.approvalBlockType = approvalBlockType;
    }

    /**
     * Возвращает заданное имя типа для константы
     */
    @Override
    public String toString() {
        return approvalBlockType;
    }
}
