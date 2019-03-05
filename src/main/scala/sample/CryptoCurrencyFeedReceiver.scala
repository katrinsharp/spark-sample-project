package sample

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import org.jfarcand.wcs.{TextListener, WebSocket}


class CryptoCurrencyFeedReceiver extends Receiver[String](StorageLevel.MEMORY_ONLY) with Runnable {

  private[this] lazy val thread: Thread = new Thread(this)

  private[this] lazy val webSocket: WebSocket =
    WebSocket().open("wss://stream.binance.com:9443/ws/ethbtc@aggTrade")

  override def onStart() = {
    thread.start()
  }

  override def onStop() = {
    webSocket.close
    thread.interrupt()
  }

  override def run() = {
    webSocket.listener(new TextListener {
      override def onMessage(message: String) {
        println(message)
        store(message)
      }
    })
  }
}
