package minicraft.item;

import minicraft.entity.Direction;
import minicraft.entity.mob.AirWizard;
import minicraft.entity.mob.Player;
import minicraft.gfx.Sprite;
import minicraft.level.Level;
import minicraft.level.tile.Tile;
import org.tinylog.Logger;

import java.util.ArrayList;

public class SummonItem extends StackableItem {

	protected static ArrayList<Item> getAllInstances() {
		ArrayList<Item> items = new ArrayList<>();

		items.add(new SummonItem("Totem of Air", new Sprite(0, 19, 0), "Air Wizard"));
		items.add(new SummonItem("Totem of Wind", new Sprite(1, 19, 0), "Air Wizard II"));

		return items;
	}

	private final String mob;

	private SummonItem(String name, Sprite sprite, String mob) { this(name, sprite, 1, mob); }
	private SummonItem(String name, Sprite sprite, int count, String mob) {
		super(name, sprite, count);
		this.mob = mob;
	}
	
	/** What happens when the player uses the item on a tile */
	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, Direction attackDir) {
		boolean success = false;
		if (mob.equals("Air Wizard")) {
			AirWizard aw = new AirWizard(false);
			level.add(aw, player.x+8, player.y+8, false);
			Logger.debug("Summoned new Air Wizard");
			success = true;
		}
		if (mob.equals("Air Wizard II")) {
			AirWizard aw = new AirWizard(true);
			level.add(aw, player.x+8, player.y+8, false);
			Logger.debug("Summoned new Air Wizard II");
			success = true;
		}
		return super.interactOn(success);
	}
	
	@Override
	public boolean interactsWithWorld() { return false; }
	
	public SummonItem clone() {
		return new SummonItem(getName(), sprite, count, mob);
	}
}
