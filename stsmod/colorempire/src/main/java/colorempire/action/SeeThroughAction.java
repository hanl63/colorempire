package colorempire.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

public class SeeThroughAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("OpeningAction");
    public static final String[] TEXT = uiStrings.TEXT;

    private int drawAmount; // 抽牌数量
    private AbstractMonster targetMonster; // 目标敌人

    public SeeThroughAction(int drawAmount, AbstractMonster targetMonster) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.drawAmount = drawAmount;
        this.targetMonster = targetMonster;
    }

    @Override
    public void update() {
        // 核心条件判断：目标敌人存在，且有明确的攻击意图（基础伤害≥0）
        if (targetMonster != null && targetMonster.getIntentBaseDmg() >= 0) {
            this.addToTop(new DrawCardAction(drawAmount));
        } else {

            AbstractDungeon.effectList.add(new ThoughtBubble(
                    AbstractDungeon.player.dialogX,
                    AbstractDungeon.player.dialogY,
                    3.0F,
                    TEXT[0],
                    true
            ));
        }
        this.isDone = true;
    }
}
