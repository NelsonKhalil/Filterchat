package io.github.bloxxxx.filterchat.config;

import io.github.bloxxxx.filterchat.Filterchat;
import io.github.bloxxxx.filterchat.util.ColorUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ConfigScreen extends Screen {
    public ConfigScreen() {
        super(Text.literal("FilterChatConfigScreen"));
    }

    TextFieldWidget testFiled;
    ButtonWidget saveButton;
    ButtonWidget closeButton;
    ButtonWidget resetButton;

    @Override
    protected void init() {
        Config config = Config.getConfig();

        this.testFiled = new TextFieldWidget(Filterchat.MC.textRenderer, 10, 10, 60, 20, Text.literal("Test Config"));
        this.testFiled.setMaxLength(3);
        this.testFiled.setText(String.valueOf(config.testConfig));
        this.addDrawableChild(this.testFiled);

        this.saveButton = ButtonWidget.builder(Text.literal("Save"), button -> saveConfig())
                .dimensions(width - 100, height - 30, 80, 20)
                .tooltip(Tooltip.of(Text.literal("Esc saves automatically")))
                .build();
        this.addDrawableChild(this.saveButton);

        this.closeButton = ButtonWidget.builder(Text.literal("Close"), button -> this.client.setScreen(null))
                .dimensions(width - 200, height - 30, 80, 20)
                .tooltip(Tooltip.of(Text.literal("Does NOT save")))
                .build();
        this.addDrawableChild(this.closeButton);

        this.resetButton = ButtonWidget.builder(Text.literal("Reset"), button -> resetConfig())
                .dimensions(width - 300, height - 30, 80, 20)
                .tooltip(Tooltip.of(Text.literal("Resets to defaults")))
                .build();
        this.addDrawableChild(this.resetButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        context.fill(0, height - 40, width, height, ColorUtil.hex("c0202020"));
        super.render(context, mouseX, mouseY, deltaTicks);
    }

    @Override
    public void resize(MinecraftClient client, int width, int height) {
        this.init(client, width, height);
    }

    @Override
    public void close() {
        saveConfig();
        this.client.setScreen(null);
    }

    private void saveConfig() {
        Config config = Config.getConfig();

        config.testConfig = Integer.parseInt(this.testFiled.getText());

        config.save();
    }

    private void resetConfig() {

    }
}
