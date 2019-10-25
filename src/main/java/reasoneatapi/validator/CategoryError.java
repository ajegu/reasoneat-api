package reasoneatapi.validator;

public enum CategoryError  {
    NAME_NOT_NULL("name.not_null", "name", "Le libellé de la catégorie est obligatoire"),
    NAME_TOO_SHORT("name.too_short", "name", "Le libellé doit contenir plus de 3 caractères"),
    NAME_TOO_LONG("name.too_long", "name", "Le libellé doit contenir moins de 50 caractères"),
    HEADER_TEXT_TOO_SHORT("headerText.too_short", "headerText", "Le texte de haut de page doit contenir plus de 50 caractères"),
    FOOTER_TEXT_TOO_SHORT("footerText.too_short", "footerText", "Le texte de bas de page doit contenir plus de 50 caractères"),
    IMAGE_TOO_LONG("image.too_long", "image", "Le lien vers l'image ne doit pas être supérieur à 255 caractères"),
    ALREADY_EXIST("category.exists","", "La catégorie existe déjà");

    private String code;
    private String field;
    private String message;

    CategoryError(String code, String field, String message) {
        this.code = code;
        this.field = field;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getField() {
        return this.field;
    }

    public String getMessage() {
        return this.message;
    }
}
