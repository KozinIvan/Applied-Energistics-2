/*
 * This file is part of Applied Energistics 2.
 * Copyright (c) 2013 - 2014, AlgorithmX2, All rights reserved.
 *
 * Applied Energistics 2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Applied Energistics 2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Applied Energistics 2.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */

package appeng.integration.modules.waila.part;

import java.util.List;

import net.minecraft.util.text.ITextComponent;

import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;

import appeng.api.implementations.parts.IStorageMonitorPart;
import appeng.api.parts.IPart;
import appeng.api.storage.data.IAEFluidStack;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.IAEStack;
import appeng.core.localization.WailaText;

/**
 * Storage monitor provider for WAILA
 *
 * @author thatsIch
 * @version rv2
 * @since rv2
 */
public final class StorageMonitorWailaDataProvider extends BasePartWailaDataProvider {
    /**
     * Displays the stack if present and if the monitor is locked. Can handle fluids and items.
     *
     * @param part     maybe storage monitor
     * @param tooltip  to be written to tooltip
     * @param accessor information wrapper
     * @param config   config option
     */
    @Override
    public void appendBody(final IPart part, final List<ITextComponent> tooltip, final IDataAccessor accessor,
            final IPluginConfig config) {
        if (part instanceof IStorageMonitorPart) {
            final IStorageMonitorPart monitor = (IStorageMonitorPart) part;

            final IAEStack<?> displayed = monitor.getDisplayed();
            final boolean isLocked = monitor.isLocked();

            // TODO: generalize
            if (displayed instanceof IAEItemStack) {
                final IAEItemStack ais = (IAEItemStack) displayed;
                tooltip.add(
                        WailaText.Showing.textComponent().copyRaw().appendString(": ")
                                .appendSibling(ais.asItemStackRepresentation().getDisplayName()));
            } else if (displayed instanceof IAEFluidStack) {
                final IAEFluidStack ais = (IAEFluidStack) displayed;
                tooltip.add(WailaText.Showing.textComponent().copyRaw().appendString(": ")
                        .appendSibling(ais.getFluidStack().getName()));
            }

            tooltip.add((isLocked) ? WailaText.Locked.textComponent() : WailaText.Unlocked.textComponent());
        }
    }
}