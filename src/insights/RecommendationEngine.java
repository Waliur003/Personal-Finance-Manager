package insights;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Generates financial recommendations based on yearly budget analysis.
 *
 * @author Waliur Sun
 */
public class RecommendationEngine {

    /**
     * Generates recommendations for a surplus budget.
     *
     * @param netBalance positive net balance
     * @param categoryPercentages spending percentages by category
     * @return list of recommendations
     */
    public List<String> generateSurplusRecommendations(
            int netBalance,
            Map<String, Double> categoryPercentages) {

        validateCategoryPercentages(categoryPercentages);

        List<String> recommendations = new ArrayList<>();

        if (netBalance <= 0) {
            recommendations.add(
                    "No surplus recommendations available.");
            return recommendations;
        }

        recommendations.add(
                "You finished the year with a surplus of $"
                        + netBalance + ".");

        recommendations.add(
                "Consider saving a portion of your surplus.");

        recommendations.add(
                "Maintain your current spending habits.");

        for (Map.Entry<String, Double> entry
                : categoryPercentages.entrySet()) {

            int adjustment = estimateCategoryAdjustments(
                    netBalance,
                    entry.getValue());

            if (adjustment > 0) {

                recommendations.add(
                        "You could safely increase "
                                + entry.getKey()
                                + " spending by approximately $"
                                + adjustment + ".");
            }
        }

        return recommendations;
    }

    /**
     * Generates recommendations for a deficit budget.
     *
     * @param netBalance negative net balance
     * @param categoryPercentages spending percentages
     * @return list of recommendations
     */
    public List<String> generateDeficitRecommendations(
            int netBalance,
            Map<String, Double> categoryPercentages) {

        validateCategoryPercentages(categoryPercentages);

        List<String> recommendations = new ArrayList<>();

        if (netBalance >= 0) {

            recommendations.add(
                    "No deficit recommendations available.");

            return recommendations;
        }

        int deficit = Math.abs(netBalance);

        recommendations.add(
                "You exceeded your income by $"
                        + deficit + ".");

        recommendations.add(
                "Reduce unnecessary expenses.");

        for (Map.Entry<String, Double> entry
                : categoryPercentages.entrySet()) {

            int reduction = estimateCategoryAdjustments(
                    deficit,
                    entry.getValue());

            if (reduction > 0) {

                recommendations.add(
                        "Reduce "
                                + entry.getKey()
                                + " spending by approximately $"
                                + reduction + ".");
            }
        }

        return recommendations;
    }

    /**
     * Finds the largest expense category.
     *
     * @param categoryPercentages category percentages
     * @return descriptive message
     */
    public String compareSpendingPatterns(
            Map<String, Double> categoryPercentages) {

        validateCategoryPercentages(categoryPercentages);

        if (categoryPercentages.isEmpty()) {
            return "No expense categories found.";
        }

        String largestCategory = "";
        double highestPercentage = -1.0;

        for (Map.Entry<String, Double> entry
                : categoryPercentages.entrySet()) {

            if (entry.getValue() > highestPercentage) {

                highestPercentage = entry.getValue();
                largestCategory = entry.getKey();
            }
        }

        return "Largest spending category: "
                + largestCategory
                + " ("
                + String.format("%.2f", highestPercentage)
                + "%)";
    }

    /**
     * Estimates category adjustment amount.
     *
     * @param balanceAmount surplus or deficit amount
     * @param categoryPercentage percentage of expenses
     * @return estimated adjustment
     */
    public int estimateCategoryAdjustments(
            int balanceAmount,
            double categoryPercentage) {

        if (balanceAmount < 0) {
            throw new IllegalArgumentException(
                    "Balance amount cannot be negative.");
        }

        if (categoryPercentage < 0) {
            throw new IllegalArgumentException(
                    "Category percentage cannot be negative.");
        }

        return (int) Math.round(
                balanceAmount * (categoryPercentage / 100.0));
    }

    /**
     * Generates recommendations based on budget status.
     *
     * @param status budget status
     * @param netBalance yearly balance
     * @param categoryPercentages expense percentages
     * @return list of recommendations
     */
    public List<String> generateRecommendations(
            BudgetStatus status,
            int netBalance,
            Map<String, Double> categoryPercentages) {

        if (status == null) {
            throw new IllegalArgumentException(
                    "Budget status cannot be null.");
        }

        List<String> recommendations = new ArrayList<>();

        recommendations.add(
                compareSpendingPatterns(categoryPercentages));

        switch (status) {

            case SURPLUS:
                recommendations.addAll(
                        generateSurplusRecommendations(
                                netBalance,
                                categoryPercentages));
                break;

            case DEFICIT:
                recommendations.addAll(
                        generateDeficitRecommendations(
                                netBalance,
                                categoryPercentages));
                break;

            case BALANCED:
                recommendations.add(
                        "Your yearly budget is balanced.");
                break;
        }

        return recommendations;
    }

    /**
     * Validates category percentages.
     *
     * @param categoryPercentages category percentages
     */
    private void validateCategoryPercentages(
            Map<String, Double> categoryPercentages) {

        if (categoryPercentages == null) {
            throw new IllegalArgumentException(
                    "Category percentages cannot be null.");
        }
    }
}