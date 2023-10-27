import Cards.*;
import Participants.*;

public class Tester {
    public static void main(String[] args){
        UnoDeck unoDeck = new UnoDeck();
        Table table = new Table();
        User user = new User();

        user.showMyCards();
    }
}
