package model.enum_;

/**
 * Enum "MemberType" - представляет собой тип участника согласования
 */
public enum MemberType {
    INITIATOR("Инициатор"),
    PARTICIPANT("Участник"),
    SIGNATORY("Подписант");
    /**
     * Поле для хранения значения константы
     */
    private final String memberType;

    /**
     * Конструктор для передачи значения константы в поле для хранения
     */
    MemberType(String memberType) {
        this.memberType = memberType;
    }

    /**
     * Возвращает заданное имя типа для константы
     */
    @Override
    public String toString() {
        return memberType;
    }
}
