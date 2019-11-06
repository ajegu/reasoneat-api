package reasoneatapi.helper;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@Service
public class SortHelper {
    /**
     * Retourne la propriété de tri valide pour une classe donnée
     * @param property propriété à tester
     * @param clazz classe DTO de référence
     * @param defaultProperty propriété de tri retournée par défaut
     * @return validProperty
     */
    public String getValidSortProperty(String property, Class clazz, String defaultProperty) {
        Field[] fields = clazz.getDeclaredFields();
        String validProperty = defaultProperty;
        for (Field field: fields) {
            if (field.getName().equals(property)) {
                validProperty = property;
                break;
            }

            if (field.isAnnotationPresent(JsonProperty.class)) {
                JsonProperty annotation = field.getDeclaredAnnotation(JsonProperty.class);
                if (annotation.value().equals(property)) {
                    validProperty = field.getName();
                    break;
                }
            }
        }

        return validProperty;
    }
}
