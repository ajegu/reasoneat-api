package reasoneatapi.validator;

public enum ProductError {
    NAME_NOT_NULL("name.not_null", "name", "Le libellé du produit est obligatoire"),
    IMAGE_NOT_NULL("image.not_null", "image", "Le lien de l'image du produit est obligatoire");

    private String code;
    private String field;
    private String message;

    ProductError(String code, String field, String message) {
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
