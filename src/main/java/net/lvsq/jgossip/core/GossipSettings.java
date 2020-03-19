// Copyright (c) 2017 The jgossip Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package net.lvsq.jgossip.core;


import net.lvsq.jgossip.model.SeedMember;
import net.lvsq.jgossip.net.MsgService;
import net.lvsq.jgossip.net.udp.UDPMsgService;

import java.util.ArrayList;
import java.util.List;


/**
 * <h3>GossipSettings</h3>
 * <p>Gossip协议的一些具体参数</p>
 *
 * @author : lvsq
 **/
public class GossipSettings {

    /** 每次gossip通信的ping间隔，默认为1秒
     * Time between gossip ping in ms. Default is 1 second */
    private int gossipInterval = 1000;

    /** 网络延迟，默认为200ms
     * Network delay in ms. Default is 200ms */
    private int networkDelay = 200;

    /** (发送接收)消息同步服务的具体实现类型，默认是UDP消息服务（UDPMsgService.class）
     * Which message sync implementation. Default is UDPMsgService.class */
    private MsgService msgService = new UDPMsgService();

    /**
     当未收到同步消息超过[deleteThreshold]次后，删除death节点，默认为3次
     * Delete the death node when the sync message is not received more than [deleteThreshold] times. Default is 3 times*/
    private int deleteThreshold = 3;

    private List<SeedMember> seedMembers;

    public int getGossipInterval() {
        return gossipInterval;
    }

    public void setGossipInterval(int gossipInterval) {
        this.gossipInterval = gossipInterval;
    }

    public int getNetworkDelay() {
        return networkDelay;
    }

    public void setNetworkDelay(int networkDelay) {
        this.networkDelay = networkDelay;
    }

    public List<SeedMember> getSeedMembers() {
        return seedMembers;
    }

     public void setSeedMembers(List<SeedMember> seedMembers) {
        List<SeedMember> _seedMembers = new ArrayList<>();
        if(seedMembers != null && !seedMembers.isEmpty()){
            for(SeedMember seed : seedMembers){
                if(!seed.eigenvalue().equalsIgnoreCase(GossipManager.getInstance().getSelf().eigenvalue())){
                    if(!_seedMembers.contains(seed)){
                        _seedMembers.add(seed);
                    }
                }
            }
        }
        this.seedMembers = seedMembers;
    }

    public MsgService getMsgService() {
        return msgService;
    }

    public void setMsgService(MsgService msgService) {
        this.msgService = msgService;
    }

    public int getDeleteThreshold() {
        return deleteThreshold;
    }

    public void setDeleteThreshold(int deleteThreshold) {
        this.deleteThreshold = deleteThreshold;
    }
}
