public interface CoffeeMachineInterface {
    void chooseFirstSelection();

    void chooseSecondSelection();
}

public class OldCoffeeMachine {
    public void selectA() {

    }

    public void selectB() {

    }
}

public class CoffeeTouchscreenAdapter implements CoffeeMachineInterface {
    private OldCoffeeMachine oldVendingMachine;

    public CoffeeTouchscreenAdapter(OldCoffeeMachine oldVendingMachine) {
        this.oldVendingMachine = oldVendingMachine
    }

    @Override
    public void chooseFirstSelection() {
        oldVendingMachine.selectA();
    }

    @Override
    public void chooseSecondSelection() {
        oldVendingMachine.selectB();
    }
}