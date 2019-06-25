package io.moquette.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.haproxy.HAProxyMessage;



public class ProxyClientAddressHandler extends SimpleChannelInboundHandler<HAProxyMessage>
{

	private static final Logger logger = LoggerFactory.getLogger( ProxyClientAddressHandler.class );



	@Override
	protected void channelRead0( ChannelHandlerContext ctx, HAProxyMessage msg ) throws Exception
	{
		logger.debug( "Proxy client address [{]] added as channel attribute", msg.sourceAddress() );
		NettyUtils.clientAddress( ctx.channel(), msg.sourceAddress() );
		ctx.pipeline().remove( this );
	}

}
