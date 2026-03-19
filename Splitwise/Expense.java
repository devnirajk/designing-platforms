class Expense extends Timestamp {
    private Integer expenseId;
    private String expenseName;
    private Integer groupId;
    private List<Participant> participants;
    private SplitStrategyType splitStrategyType;
    private SplitStrategy splitStrategy;
    private double amount;
    private User paidBy;
}