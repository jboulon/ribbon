/*
 *
 * Copyright 2013 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.netflix.loadbalancer;

import com.netflix.client.config.IClientConfig;

/**
 * This class essentially contains the ResponseTimeWeightedRule class defined in
 * the loadbalancer package
 * 
 * @author stonse
 * 
 */
public class WeightedResponseTimeRule extends AbstractLoadBalancerRule {

    ResponseTimeWeightedRule rule = new ResponseTimeWeightedRule();

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        rule = new ResponseTimeWeightedRule();
    }

    @Override
    // TODO(stonse): Consider refactoring this so that we dont need to override
    // this
    public void setLoadBalancer(AbstractLoadBalancer lb) {
        super.setLoadBalancer(lb);
        rule.setLoadBalancer(lb);// set it for the contained Rule class
        rule.initialize(lb);
    }

    @Override
    public Server choose(BaseLoadBalancer lb, Object key) {
        if (rule != null) {
            return rule.choose(lb, key);
        } else {
            throw new IllegalArgumentException(
                    "This class has not been initialized with the RoundRobinRule class");
        }
    }

}
