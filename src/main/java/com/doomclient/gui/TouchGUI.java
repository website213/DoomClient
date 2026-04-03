package com.doomclient.gui;

import com.doomclient.module.Module;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

/**
 * TouchGUI - Simplified GUI for Doom Client.
 */
public class TouchGUI extends Screen {
    
    public TouchGUI() {
        super(Text.literal("Doom Client"));
    }

    @Override
    public void render(net.minecraft.client.gui.DrawContext context, int mouseX, int mouseY, float partialTickTime) {
        this.fillGradient(context, 0, 0, this.width, this.height, 0xFF000000, 0xFF1111AA);
        context.drawCenteredTextWithShadow(this.textRenderer, "Doom Client GUI", this.width / 2, 10, 0xFFFFFF);
    }
    
    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
        this.selectedModule = modules.isEmpty() ? null : modules.get(0);
        this.scrollOffset = 0;
        this.visible = true;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);

        int startX = PADDING;
        int startY = PADDING;

        // Draw title
        this.textRenderer.draw(matrices, "Doom Client", startX + 5, startY + 5, 0xFFFFFF);

        // Draw modules list
        drawModulesList(matrices, startX, startY + 30, mouseX, mouseY);

        // Draw selected module settings
        if (selectedModule != null) {
            drawModuleSettings(matrices, startX + SIDEBAR_WIDTH + PADDING, startY, mouseX, mouseY);
        }

        super.render(matrices, mouseX, mouseY, delta);
    }

    private void drawModulesList(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
        int listHeight = this.height - y - PADDING;
        int maxVisible = listHeight / ENTRY_HEIGHT;

        for (int i = 0; i < modules.size() && i < maxVisible; i++) {
            Module module = modules.get(i);
            int itemY = y + (i * ENTRY_HEIGHT);
            boolean hovering = mouseX > x && mouseX < x + SIDEBAR_WIDTH && mouseY > itemY && mouseY < itemY + ENTRY_HEIGHT;
            boolean selected = module == selectedModule;

            // Background color
            int bgColor = selected ? 0xFF2a2a2a : (hovering ? 0xFF1a1a1a : 0xFF0a0a0a);
            fill(matrices, x, itemY, x + SIDEBAR_WIDTH, itemY + ENTRY_HEIGHT, bgColor);

            // Text color
            int textColor = module.isEnabled() ? 0x00FF00 : 0xFFFFFF;
            String label = module.getName() + (module.isEnabled() ? " ✓" : "");
            this.textRenderer.draw(matrices, label, x + 5, itemY + 8, textColor);
        }
    }

    private void drawModuleSettings(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
        if (selectedModule == null) return;

        // Module name and status
        this.textRenderer.draw(matrices, selectedModule.getName(), x + 5, y + 5, 0xFFFFFF);
        String status = selectedModule.isEnabled() ? "§aEnabled" : "§cDisabled";
        this.textRenderer.draw(matrices, status, x + 5, y + 20, 0xFFFFFF);

        // Toggle button
        int toggleY = y + 35;
        fill(matrices, x, toggleY, x + 150, toggleY + 25, 0xFF333333);
        String toggleText = selectedModule.isEnabled() ? "Disable" : "Enable";
        this.textRenderer.draw(matrices, toggleText, x + 50, toggleY + 8, 0xFFFFFF);

        // Draw settings
        int settingY = toggleY + 35;
        List<Setting<?>> settings = selectedModule.getSettings();

        for (int i = 0; i < settings.size(); i++) {
            Setting<?> setting = settings.get(i);
            drawSetting(matrices, setting, x, settingY + (i * 50), mouseX, mouseY);
        }
    }

    private void drawSetting(MatrixStack matrices, Setting<?> setting, int x, int y, int mouseX, int mouseY) {
        this.textRenderer.draw(matrices, setting.getName(), x + 5, y + 5, 0xCCCCCC);

        if (setting instanceof BooleanSetting) {
            drawBooleanSetting(matrices, (BooleanSetting) setting, x, y, mouseX, mouseY);
        } else if (setting instanceof NumberSetting) {
            drawNumberSetting(matrices, (NumberSetting) setting, x, y, mouseX, mouseY);
        } else if (setting instanceof ModeSetting) {
            drawModeSetting(matrices, (ModeSetting) setting, x, y, mouseX, mouseY);
        }
    }

    private void drawBooleanSetting(MatrixStack matrices, BooleanSetting setting, int x, int y, int mouseX, int mouseY) {
        int boxX = x + 200;
        int boxY = y + 3;
        int boxSize = 15;

        fill(matrices, boxX, boxY, boxX + boxSize, boxY + boxSize, setting.getValue() ? 0xFF00FF00 : 0xFF555555);

        if (setting.getValue()) {
            this.textRenderer.draw(matrices, "✓", boxX + 4, boxY + 2, 0xFFFFFF);
        }
    }

    private void drawNumberSetting(MatrixStack matrices, NumberSetting setting, int x, int y, int mouseX, int mouseY) {
        // Draw slider
        int sliderX = x + 5;
        int sliderY = y + 20;
        int sliderWidth = 200;
        int sliderHeight = 10;

        fill(matrices, sliderX, sliderY, sliderX + sliderWidth, sliderY + sliderHeight, 0xFF333333);

        // Draw filled portion
        double percentage = (setting.getValue() - setting.getMin()) / (setting.getMax() - setting.getMin());
        int filledWidth = (int) (sliderWidth * percentage);
        fill(matrices, sliderX, sliderY, sliderX + filledWidth, sliderY + sliderHeight, 0xFF00FF00);

        // Draw value text
        String text = String.format("%.2f", setting.getValue());
        this.textRenderer.draw(matrices, text, x + 210, y + 20, 0xFFFFFF);
    }

    private void drawModeSetting(MatrixStack matrices, ModeSetting setting, int x, int y, int mouseX, int mouseY) {
        this.textRenderer.draw(matrices, setting.getValue(), x + 5, y + 20, 0x00FFFF);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        scrollOffset += (int) verticalAmount * 5;
        return false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) { // Left click
            // Check module list click
            int startY = PADDING + 30;
            for (int i = 0; i < modules.size(); i++) {
                int itemY = startY + (i * ENTRY_HEIGHT);
                if (mouseX > PADDING && mouseX < PADDING + SIDEBAR_WIDTH && 
                    mouseY > itemY && mouseY < itemY + ENTRY_HEIGHT) {
                    selectedModule = modules.get(i);
                    return true;
                }
            }

            // Check settings click
            if (selectedModule != null) {
                // Check toggle button
                int toggleY = PADDING + 65;
                if (mouseX > PADDING + SIDEBAR_WIDTH + PADDING && 
                    mouseX < PADDING + SIDEBAR_WIDTH + PADDING + 150 &&
                    mouseY > toggleY && mouseY < toggleY + 25) {
                    selectedModule.toggle();
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) { // ESC key
            this.close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void close() {
        super.close();
        this.visible = false;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
