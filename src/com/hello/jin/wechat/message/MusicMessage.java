package com.hello.jin.wechat.message;

import com.hello.jin.wechat.base.Music;

/**
 * 音乐消息  响应消息之音乐消息
 *
 */
public class MusicMessage extends BaseMessage {
    // 音乐
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}