class PercentWiseSplitStrategy implements SplitStrategy {
    @Override
    boolean split(List<Participant> participantList, double amount) {
        double currentTotalPercent = 0;
        for(Participant participant : participantList) {
            currentTotalPercent+=participant.getPercent();
            double percentAmount = (amount * participant.getPercent()) / 100;
            participant.setAmount(percentAmount);
        }

        if(currentTotalPercent != 100) {
           return false;
        }

        return true;
    }
}