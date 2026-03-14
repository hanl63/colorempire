package colorempire.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class SneakAttackAction extends AbstractGameAction {
    private DamageInfo info;

    private static final float DURATION = 0.01F;

    private static final float POST_ATTACK_WAIT_DUR = 0.1F;

    private AbstractMonster m;

    public SneakAttackAction(AbstractMonster target, DamageInfo info) {
        this.info = info;
        setValues((AbstractCreature)target, info);
        this.m = target;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.attackEffect = AbstractGameAction.AttackEffect.SLASH_VERTICAL;
        this.duration = 0.01F;
    }

    public void update() {
        if (this.target == null) {
            this.isDone = true;
            return;
        }
        if (isNonAttackIntent(this.m.intent)) {
            if (this.duration == 0.01F && this.target != null && this.target.currentHealth > 0) {
                if (this.info.type != DamageInfo.DamageType.THORNS &&
                        this.info.owner.isDying) {
                    this.isDone = true;
                    return;
                }
                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
            }
            tickDuration();
            if (this.isDone && this.target != null && this.target.currentHealth > 0) {
                this.target.damage(this.info);
                if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
                    AbstractDungeon.actionManager.clearPostCombatActions();
                addToTop((AbstractGameAction)new WaitAction(0.1F));
            }
        } else {
            this.isDone = true;
        }
    }
    private boolean isNonAttackIntent(AbstractMonster.Intent intent) {
        return intent != AbstractMonster.Intent.ATTACK &&
                intent != AbstractMonster.Intent.ATTACK_BUFF &&
                intent != AbstractMonster.Intent.ATTACK_DEBUFF &&
                intent != AbstractMonster.Intent.ATTACK_DEFEND;
    }
}
