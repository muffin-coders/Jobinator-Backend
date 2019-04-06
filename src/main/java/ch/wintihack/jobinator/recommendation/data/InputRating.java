package ch.wintihack.jobinator.recommendation.data;

import lombok.Data;

@Data
public class InputRating {
    private int userId;
    private int productId;
    private double rating;

    public InputRating(int userId, int productId, double rating) {
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
    }

}
