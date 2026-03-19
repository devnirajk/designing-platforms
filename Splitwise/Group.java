class Group extends Timestamp {
    private Integer groupId;
    private String groupName;
    private List<User> members;
    private List<Expense> expenseList;

    void settling() {
        // UserId (who paid earlier) -> <UserId who will return money , how much>
        HashMap<Integer, HashMap<Integer, Double>> map = new HashMap<Integer, HashMap<Integer, Double>>();
        for(Expense e : expenseList) {
            // Check if the current expense payer is already in map as payer for some other expense;
            map.putIfAbsent(e.getPaidBy().getUserId(), new HashMap<>());

            // now we have data for a given user for all expense till now he have paid for
            HashMap<Integer, Double> payerList = map.get(e.getPaidBy().getUserId());

            // get the list of participants because we have to update amount
            List<Participant> participants = e.getParticipants();

            for(Participant p : participants) {
                if(e.getPaidBy().getUserId() != p.getUserId()) {
                payerList.putIfAbsent(p.getUserId(), 0.0);
                double amount = payerList.get(p.getUserId()) + p.getAmount();
                payerList.put(p.getUserId(), amount);
                }
            }
            map.put(e.getPaidBy().getUserId(), payerList);
        }
    }
}