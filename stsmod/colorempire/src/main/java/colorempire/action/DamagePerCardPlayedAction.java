package colorempire.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DamagePerCardPlayedAction extends AbstractGameAction {
    private DamageInfo info;

    public DamagePerCardPlayedAction(AbstractCreature target, DamageInfo info, AbstractGameAction.AttackEffect effect) {
        this.info = info;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.attackEffect = effect;
    }

    public DamagePerCardPlayedAction(AbstractCreature target, DamageInfo info) {
        this(target, info, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void update() {
        this.isDone = true;

        if (this.target != null && this.target.currentHealth > 0) {
            int count = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();

            count--;

            for (int i = 0; i < count; i++) {
                addToTop((AbstractGameAction)new DamageAction(
                        this.target,
                        this.info,
                        this.attackEffect
                ));
            }
        }
    }
}
