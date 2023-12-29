/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package appeng.api.events;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.resources.ResourceLocation;

/**
 * Data of an {@link ArrayBackedEvent} phase.
 */
class EventPhaseData<T> {
    final ResourceLocation id;
    T[] listeners;
    final List<EventPhaseData<T>> subsequentPhases = new ArrayList<>();
    final List<EventPhaseData<T>> previousPhases = new ArrayList<>();
    int visitStatus = 0; // 0: not visited, 1: visiting, 2: visited

    @SuppressWarnings("unchecked")
    EventPhaseData(ResourceLocation id, Class<?> listenerClass) {
        this.id = id;
        this.listeners = (T[]) Array.newInstance(listenerClass, 0);
    }

    void addListener(T listener) {
        int oldLength = listeners.length;
        listeners = Arrays.copyOf(listeners, oldLength + 1);
        listeners[oldLength] = listener;
    }
}