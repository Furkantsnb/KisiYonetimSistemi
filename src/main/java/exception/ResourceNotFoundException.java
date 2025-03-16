package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Object... searchParams) {
        super(generateMessage(resourceName, searchParams));
    }

    private static String generateMessage(String resourceName, Object[] searchParams) {
        if (searchParams == null || searchParams.length == 0) {
            return resourceName + " not found.";
        }

        StringBuilder message = new StringBuilder(resourceName);
        message.append(" not found with parameters ");

        for (int i = 0; i < searchParams.length; i += 2) {
            if (i > 0) {
                message.append(", ");
            }
            message.append(searchParams[i]).append(": '").append(searchParams[i + 1]).append("'");
        }
        return message.toString();
    }
}