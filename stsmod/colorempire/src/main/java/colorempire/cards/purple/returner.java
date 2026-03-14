package colorempire.cards.purple;

import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import colorempire.cards.black.peacewithin;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class returner extends CustomCard {
    public static final String ID = "color:returner";
    private static final String NAME = "求生本能";
    private static final String IMG_PATH = "ExampleModResources/img/cards/Strike.png";
    private static final int COST = -3;
    private static final String DESCRIPTION = "这张牌被消耗时, NL 获得 !M! 层灵体 !M! 层缓冲。";
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.PURPLE;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;

    public returner() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void receiveEditCards() {
        BaseMod.addCard(new peacewithin());
    }
}