package com.hello.jin.wechat.message;

import com.hello.jin.wechat.base.Music;

/**
 * ������Ϣ  ��Ӧ��Ϣ֮������Ϣ
 *
 */
public class MusicMessage extends BaseMessage {
    // ����
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}