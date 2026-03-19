class EqualSplitStrategy implements SplitStrategy {
    @Override
    boolean split(List<Participant> participantList, double amount) {
        double equalAmount = amount/participantList.size();
        for(Participant participant : participantList) {
            participant.setAmount(equalAmount);
        }
        return true;
    }
}