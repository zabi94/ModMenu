package io.github.prospector.modmenu.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.EntryListWidget;

public class DescriptionListWidget extends EntryListWidget<DescriptionListWidget.DescriptionItem> {

	private ModListEntry lastSelected = null;
	private ModListScreen parent;
	private TextRenderer textRenderer;

	public DescriptionListWidget(MinecraftClient client, int width, int height, int top, int bottom, int entryHeight, ModListScreen parent) {
		super(client, width, height, top, bottom, entryHeight);
		this.parent = parent;
		this.textRenderer = client.textRenderer;
	}

	@Override
	public DescriptionItem getSelected() {
		return null;
	}

	@Override
	public int getRowWidth() {
		return this.width - 10;
	}

	@Override
	protected int getScrollbarPosition() {
		return this.width - 6 + left;
	}

	@Override
	public void render(int int_1, int int_2, float float_1) {
		if (parent.getModList().getSelected() != lastSelected) {
			lastSelected = parent.getModList().getSelected();
			clearEntries();
			setScrollAmount(-Double.MAX_VALUE);
			if (lastSelected != null && lastSelected.metadata.getDescription() != null && !lastSelected.metadata.getDescription().isEmpty())
				for (String line : textRenderer.wrapStringToWidthAsList(lastSelected.metadata.getDescription().replaceAll("\n", "\n\n"), getRowWidth()))
					children().add(new DescriptionItem(line));
		}
		super.render(int_1, int_2, float_1);
	}

	protected class DescriptionItem extends EntryListWidget.Entry<DescriptionItem> {
		protected String text;

		public DescriptionItem(String text) {
			this.text = text;
		}

		@Override
		public void render(int index, int y, int x, int itemWidth, int itemHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
			MinecraftClient.getInstance().textRenderer.drawWithShadow(text, x, y, 0xAAAAAA);
		}
	}

}
