package colorempire.modcore;


import colorempire.cards.black.*;
import colorempire.characters.black;
import colorempire.relics.Adaptation;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.badlogic.gdx.graphics.Color;


import static colorempire.characters.black.PlayerColorEnum.GRAY;

@SpireInitializer
public class colorempire implements
        EditCardsSubscriber,
        EditStringsSubscriber,
        EditCharactersSubscriber,
        EditRelicsSubscriber {
    // 人物选择界面按钮的图片
    private static final String MY_CHARACTER_BUTTON = "resources/img/char/Character_Button.png";
    // 人物选择界面的立绘
    private static final String MY_CHARACTER_PORTRAIT = "resources/img/char/Character_Portrait.png";
    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = "resources/img/512/bg_attack_512.png";
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = "resources/img/512/bg_power_512.png";
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = "resources/img/512/bg_skill_512.png";
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = "resources/img/char/small_orb.png";
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = "resources/img/1024/bg_attack.png";
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = "resources/img/1024/bg_power.png";
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = "resources/img/1024/bg_skill.png";
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = "resources/img/char/card_orb.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENEYGY_ORB = "resources/img/char/cost_orb.png";
    public static final Color black_COLOR = new Color(158.0F / 255.0F, 158.0F / 255.0F, 158.0F / 255.0F, 1.0F);

    public colorempire() {
        BaseMod.subscribe(this);
        BaseMod.addColor(GRAY, black_COLOR, black_COLOR, black_COLOR, black_COLOR, black_COLOR, black_COLOR, black_COLOR,BG_ATTACK_512,BG_SKILL_512,BG_POWER_512,ENEYGY_ORB,BG_ATTACK_1024,BG_SKILL_1024,BG_POWER_1024,BIG_ORB,SMALL_ORB);
    }

    public static void initialize() {
        new colorempire();
    }

    @Override
    public void receiveEditCharacters() {
        // 向basemod注册人物
        BaseMod.addCharacter(new black(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, black.PlayerColorEnum.BLACK);
    }

    // 当basemod开始注册mod卡牌时，便会调用这个函数
    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new Strike_BLACK());
        BaseMod.addCard(new peacewithin());
        BaseMod.addCard(new survival_instinct());
        BaseMod.addCard(new see_through());
        BaseMod.addCard(new sword_dance());
        BaseMod.addCard(new vision_of_inter_demon());
        BaseMod.addCard(new vibes());
        BaseMod.addCard(new golden_armour());
        BaseMod.addCard(new dark_embrace());
        BaseMod.addCard(new inter_demon());
        BaseMod.addCard(new sneak_attack());
        BaseMod.addCard(new bloodthirsty());
        BaseMod.addCard(new garrison());



    }

    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS"; // 如果语言设置为简体中文，则加载ZHS文件夹的资源
        } else {
            lang = "ENG"; // 如果没有相应语言的版本，默认加载英语
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, " resources/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, " resources/localization/" + lang + "/characters.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "resources/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "resources/localization/" + lang + "/powers.json");
        // 如果是中文，加载的就是"ExampleResources/localization/ZHS/cards.json"
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new Adaptation(), GRAY);
    }
}




