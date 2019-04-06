package ch.wintihack.jobinator.recommendation.service;

import ch.wintihack.jobinator.recommendation.data.InputRating;
import ch.wintihack.jobinator.recommendation.exceptions.ModelNotReadyException;
import ch.wintihack.jobinator.recommendation.model.RecommendationMlModel;

import java.util.Collection;
import java.util.concurrent.Callable;

public class RecommendationService {

    private final RecommendationMlModel recommendationMlModel;

    public RecommendationService(Collection<InputRating> ratings) {
        recommendationMlModel = new RecommendationMlModel(ratings);
    }

    public RecommendationService(Callable<Collection<InputRating>> ratings, long retrainTime, long initialDelay) {
        recommendationMlModel = new RecommendationMlModel(ratings, retrainTime, initialDelay);
    }

    public boolean isModelReady() {
        return recommendationMlModel.isModelReady();
    }

    public Double getPrediction(Integer userId, Integer productId) {
        try {
            return recommendationMlModel.getInterestPrediction(userId, productId);
        } catch (ModelNotReadyException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public void close() {
        recommendationMlModel.close();
    }

    /**
     * returns the current version of the model (+1 for each new model)
     */
    public Integer getModelNumber() {
        return recommendationMlModel.getModelNumber();
    }
}
