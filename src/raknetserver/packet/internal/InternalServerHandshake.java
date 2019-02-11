package raknetserver.packet.internal;

import java.net.InetSocketAddress;

import io.netty.buffer.ByteBuf;
import raknetserver.packet.RakNetConstants;
import raknetserver.packet.RakNetDataSerializer;

public class InternalServerHandshake extends AbstractInternalPacket {

	private InetSocketAddress clientAddr;
	private long timestamp;
	protected Reliability reliability = Reliability.RELIABLE;

	public InternalServerHandshake() {

	}

	public InternalServerHandshake(InetSocketAddress clientAddr, long timestamp) {
		this.clientAddr = clientAddr;
		this.timestamp = timestamp;
	}

	@Override
	public void decode(ByteBuf buf) {
		//TODO: real decode
		buf.skipBytes(buf.readableBytes());
	}

	@Override
	public void encode(ByteBuf buf) {
		RakNetDataSerializer.writeAddress(buf, clientAddr);
		buf.writeShort(0);
		for (int i = 0; i < 20; i++) {
			RakNetDataSerializer.writeAddress(buf, RakNetConstants.NULL_ADDR);
		}
		buf.writeLong(timestamp);
		buf.writeLong(System.currentTimeMillis());
	}

}
