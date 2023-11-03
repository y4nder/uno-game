package UnoGameFiles.Misc;
import java.util.Deque;
import UnoGameFiles.Cards.NormalCard;
import UnoGameFiles.Cards.SpecialCard;
import UnoGameFiles.Cards.UnoCard;
import UnoGameFiles.Cards.CardAttributes.Color;
import UnoGameFiles.Cards.CardAttributes.SpecialType;

public interface CardAdder {
    public void addTo(Deque<UnoCard> unoDeck, int copies, Color color, Object value);

    public class AddNormalCard implements CardAdder{

        @Override
        public void addTo(Deque<UnoCard> unoDeck, int copies, Color color, Object value) {
            for(int i = 0; i < copies; i++){
                unoDeck.add(new NormalCard(color, ((Integer)value)));
            }
        }

    }

    public class AddSpecialCard implements CardAdder{

        @Override
        public void addTo(Deque<UnoCard> unoDeck, int copies, Color color, Object value) {
            for(int i = 0; i < copies; i++){
                unoDeck.add(new SpecialCard(color, ((SpecialType)value)));
            }
        }

    }
}
