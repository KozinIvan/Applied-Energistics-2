package appeng.core.sync.packets;

import io.netty.buffer.Unpooled;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import appeng.client.gui.me.patternaccess.PatternAccessTermScreen;
import appeng.core.sync.BasePacket;

/**
 * Clears all data from the pattern access terminal before a full reset.
 */
public class ClearPatternAccessTerminalPacket extends BasePacket {

    public ClearPatternAccessTerminalPacket(FriendlyByteBuf stream) {
    }

    // api
    public ClearPatternAccessTerminalPacket() {
        FriendlyByteBuf data = new FriendlyByteBuf(Unpooled.buffer(16));
        data.writeInt(this.getPacketID());
        this.configureWrite(data);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void clientPacketData(Player player) {
        if (Minecraft.getInstance().screen instanceof PatternAccessTermScreen<?>patternAccessTerminal) {
            patternAccessTerminal.clear();
        }
    }
}
