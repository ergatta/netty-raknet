package raknet.pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import raknet.RakNet;
import raknet.packet.PacketData;

public class ReadHandler extends SimpleChannelInboundHandler<PacketData> {

    public static final String NAME = "rn-read";

    protected void channelRead0(ChannelHandlerContext ctx, PacketData packet) {
        final RakNet.Config config = (RakNet.Config) ctx.channel().config();
        final int userDataId = config.getUserDataId();
        assert !packet.isFragment();
        if (userDataId != -1 && userDataId == packet.getPacketId()) {
            ctx.fireChannelRead(packet.createData().skipBytes(1));
        } else {
            ctx.fireChannelRead(packet.retain());
        }
    }

}