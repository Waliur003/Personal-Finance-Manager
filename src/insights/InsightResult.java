package insights_fixed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Creates financial recommendations based on surplus, deficit, and spending patterns.
 *
 * @author Waliur Sun
 */
public class RecommendationEngine {

    /**
     * Constructs a new, stateless recommendation engine.
     */
    public RecommendationEngine() {
    }

    /**
     * Generates recommendations for a budget that ended with extra money,
     * limited to the categories the user selected.
     *
     * @param netBalance positive yearly balance
     * @param categoryPercentages percentage of spending for each expense category
     * @param selectedCategories the categories the user chose to receive recommendations for;
     *                            if null or empty, every category in {@code categoryPercentages} is used
     * @return list of recommendation messages
     * Author: Waliur Sun
     */
    public List<String> generateSurplusRecommendations(
            int netBalance, Map<String, Double> categoryPercentages, List<String> selectedCategories) {
        validateCategoryPercentages(categoryPercentages);

        List<String> recommendations = new ArrayList<>();

        if (netBalance <= 0) {
            recommendations.add("No surplus recommendation is available because the budget does not have a surplus.");
            return recommendations;
        }

        recommendations.add("You ended the year with a surplus of $" + netBalance + ".");
        recommendations.add("Consider saving part of the surplus before increasing spending.");

        for (Map.Entry<String, Double> entry : categoryPercentages.entrySet()) {
            if (!isCategorySelected(entry.getKey(), selectedCategories)) {
                continue;
            }
            int suggestedIncrease = estimateCategoryAdjustments(netBalance, entry.getValue());
            if (suggestedIncrease > 0) {
                recommendations.add("Based on your pattern, you could add about $" + suggestedIncrease
                        + " to " + entry.getKey() + " and still remain within the surplus.");
            }
        }

        return recommendations;
    }

    /**
     * Generates recommendations for a budget that ended in deficit, limited
     * to the categories the user selected.
     *
     * @param netBalance negative yearly balance
     * @param categoryPercentages percentage of spending for each expense category
     * @param selectedCategories the categories the user chose to receive recommendations for;
     *                            if null or empty, every category in {@code categoryPercentages} is used
     * @return list of recommendation messages
     * Author: Waliur Sun
     */
    public List<String> generateDeficitRecommendations(
            int netBalance, Map<String, Double> categoryPercentages, List<String> selectedCategories) {
        validateCategoryPercentages(categoryPercentages);

        List<String> recommendations = new ArrayList<>();

        if (netBalance >= 0) {
            recommendations.add("No deficit recommendation is available because the budget does not have a deficit.");
            return recommendations;
        }

        int deficitAmount = Math.abs(netBalance);
        recommendations.add("You ended the year with a deficit of $" + deficitAmount + ".");
        recommendations.add("To balance your budget, reduce selected expenses by about $" + deficitAmount + " total.");

        for (Map.Entry<String, Double> entry : categoryPercentages.entrySet()) {
            if (!isCategorySelected(entry.getKey(), selectedCategories)) {
                continue;
            }
            int suggestedReduction = estimateCategoryAdjustments(deficitAmount, entry.getValue());
            if (suggestedReduction > 0) {
                recommendations.add("A proportional reduction for " + entry.getKey() + " is about $"
                        + suggestedReduction + ".");
            }
        }

        return recommendations;
    }

    /**
     * Checks whether a category should receive a recommendation.
     *
     * @param category the category being considered
     * @param selectedCategories the categories the user chose; if null or empty, every category is allowed
     * @return true if the category should receive a recommendation
     * Author: Waliur Sun
     */
    private boolean isCategorySelected(String category, List<String> selectedCategories) {
        return selectedCategories == null || selectedCategories.isEmpty() || selectedCategories.contains(category);
    }

    /**
     * Compares category percentages and identifies the largest spending category.
     *
     * @param categoryPercentages percentage of spending for each expense category
     * @return message describing the highest spending category
     * Author: Waliur Sun
     */
    public String compareSpendingPatterns(Map<String, Double> categoryPercentages) {
        validateCategoryPercentages(categoryPercentages);

        if (categoryPercentages.isEmpty()) {
            return "No spending pattern is available because there are no expense categories.";
        }

        String highestCategory = "";
        double highestPercentage = 0.0;

        for (Map.Entry<String, Double> entry : categoryPercentages.entrySet()) {
            if (entry.getValue() > highestPercentage) {
                highestCategory = entry.getKey();
                highestPercentage = entry.getValue();
            }
        }

        return "Your largest spending category is " + highestCategory
                + ", which represents " + String.format("%.2f", highestPercentage) + "% of total expenses.";
    }

    /**
     * Estimates how much money should be adjusted for one category based on its percentage.
     *
     * @param balanceAmount surplus or deficit amount as a positive number
     * @param categoryPercentage category percentage of total expenses
     * @return estimated adjustment amount
     * Author: Waliur Sun
     */
    public int estimateCategoryAdjustments(int balanceAmount, double categoryPercentage) {
        if (balanceAmount < 0) {
            throw new IllegalArgumentException("Balance amount cannot be negative.");
        }
        if (categoryPercentage < 0) {
            throw new IllegalArgumentException("Category percentage cannot be negative.");
        }

        return (int) Math.round(balanceAmount * (categoryPercentage / 100.0));
    }

    /**
     * Generates recommendations for any budget status, covering every
     * expense category. Equivalent to calling
     * {@link #generateRecommendations(BudgetStatus, int, Map, List)} with a
     * null category selection.
     *
     * @param budgetStatus surplus, deficit, or balanced
     * @param netBalance income minus expenses
     * @param categoryPercentages percentage of spending for each expense category
     * @return list of recommendation messages
     * Author: Waliur Sun
     */
    public List<String> generateRecommendations(
            BudgetStatus budgetStatus,
            int netBalance,
            Map<String, Double> categoryPercentages) {
        return generateRecommendations(budgetStatus, netBalance, categoryPercentages, null);
    }

    /**
     * Generates recommendations for any budget status, limited to the
     * categories the user selected.
     *
     * @param budgetStatus surplus, deficit, or balanced
     * @param netBalance income minus expenses
     * @param categoryPercentages percentage of spending for each expense category
     * @param selectedCategories the categories the user chose to receive recommendations for;
     *                            if null or empty, every category in {@code categoryPercentages} is used
     * @return list of recommendation messages
     * Author: Waliur Sun
     */
    public List<String> generateRecommendations(
            BudgetStatus budgetStatus,
            int netBalance,
            Map<String, Double> categoryPercentages,
            List<String> selectedCategories) {

        if (budgetStatus == null) {
            throw new IllegalArgumentException("Budget status cannot be null.");
        }

        List<String> recommendations = new ArrayList<>();
        recommendations.add(compareSpendingPatterns(categoryPercentages));

        if (budgetStatus == BudgetStatus.SURPLUS) {
            recommendations.addAll(generateSurplusRecommendations(netBalance, categoryPercentages, selectedCategories));
        } else if (budgetStatus == BudgetStatus.DEFICIT) {
            recommendations.addAll(generateDeficitRecommendations(netBalance, categoryPercentages, selectedCategories));
        } else {
            recommendations.add("Your budget is balanced. Keep monitoring your spending to stay on track.");
        }

        return recommendations;
    }

    /**
     * Validates the category percentage map.
     *
     * @param categoryPercentages percentage of spending for each expense category
     * Author: Waliur Sun
     */
    private void validateCategoryPercentages(Map<String, Double> categoryPercentages) {
        if (categoryPercentages == null) {
            throw new IllegalArgumentException("Category percentages cannot be null.");
        }
    }
}