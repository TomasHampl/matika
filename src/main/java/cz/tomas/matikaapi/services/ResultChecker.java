package cz.tomas.matikaapi.services;

import cz.tomas.matikaapi.dto.VerificationResponse;
import cz.tomas.matikaapi.dto.requests.VerificationRequestBody;

import java.util.List;

/**
 * Accepts the results and indicates if they are correct or not
 */
public interface ResultChecker {

    /**
     * Verifies if the provided math result is actually {@code correct}. It does this by calculating the correct value
     * itself and then comparing against the provided values.
     * @param requestBody is an instance of {@link VerificationRequestBody} object, that contains the input values
     *                   provided to the application.
     * @return new {@link VerificationResponse} object that contains information if the provided {@code result} was correct.
     */
    public VerificationResponse validateResult(VerificationRequestBody requestBody);

    /**
     * Verifies the provided {@link VerificationRequestBody} objects that are passed as a {@link List}.
     * Uses the same verification logic as {@link ResultChecker#validateResult(VerificationRequestBody)} but works with
     * more than one objects.
     * @param requestBodies is a {@code List} of {@code VerificationRequestBody} objects that represent a multitude of
     *                      {@code VerificationRequestBody}
     * @return a {@code List} of {@code VerificationResponse}, each element in the list represents one result to be checked.
     */
    public List<VerificationResponse> validateResults(List<VerificationRequestBody> requestBodies);
}
