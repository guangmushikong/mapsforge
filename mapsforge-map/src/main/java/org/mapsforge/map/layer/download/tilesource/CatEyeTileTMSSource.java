/*
 * Copyright 2014-2016 devemux86
 *
 * This program is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.mapsforge.map.layer.download.tilesource;

import org.mapsforge.core.model.Tile;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * http://54.223.166.139:8080/tms/1.0.0/world_satellite_raster@EPSG:900913@jpeg/{z}/{x}/{y}.jpeg
 */
public class CatEyeTileTMSSource extends AbstractTileSource {
    private boolean alpha = false;
    private String baseUrl = "/";
    private String extension = "png";
    private String name;
    private int parallelRequestsLimit = 8;
    private String protocol = "http";
    private int tileSize = 256;
    private byte zoomLevelMax = 18;
    private byte zoomLevelMin = 0;

    public CatEyeTileTMSSource(String[] hostNames, int port) {
        super(hostNames, port);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof CatEyeTileTMSSource)) {
            return false;
        }
        CatEyeTileTMSSource other = (CatEyeTileTMSSource) obj;
        if (!this.baseUrl.equals(other.baseUrl)) {
            return false;
        }
        return true;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getExtension() {
        return extension;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getParallelRequestsLimit() {
        return parallelRequestsLimit;
    }

    public String getProtocol() {
        return protocol;
    }

    public int getTileSize() {
        return tileSize;
    }

    @Override
    public URL getTileUrl(Tile tile) throws MalformedURLException {
        StringBuilder stringBuilder = new StringBuilder(32);

        stringBuilder.append(baseUrl);
        stringBuilder.append(tile.zoomLevel);
        stringBuilder.append('/');
        stringBuilder.append(tile.tileX);
        stringBuilder.append('/');
        stringBuilder.append(tile.getMaxTileNumber(tile.zoomLevel) - tile.tileY);//tms方式相对于xyz服务，y轴取值不需要做转换，mapsforge自动做了转换，此处用tilesize-tileY再将y轴取值转换回来

        if (extension != null && !"".equals(extension.trim())) {
            stringBuilder.append('.').append(extension);
        }

        return new URL(this.protocol, getHostName(), this.port, stringBuilder.toString());
    }

    @Override
    public byte getZoomLevelMax() {
        return zoomLevelMax;
    }

    @Override
    public byte getZoomLevelMin() {
        return zoomLevelMin;
    }

    @Override
    public boolean hasAlpha() {
        return alpha;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + this.baseUrl.hashCode();
        return result;
    }

    public CatEyeTileTMSSource setAlpha(boolean alpha) {
        this.alpha = alpha;
        return this;
    }

    public CatEyeTileTMSSource setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public CatEyeTileTMSSource setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public CatEyeTileTMSSource setName(String name) {
        this.name = name;
        return this;
    }

    public CatEyeTileTMSSource setParallelRequestsLimit(int parallelRequestsLimit) {
        this.parallelRequestsLimit = parallelRequestsLimit;
        return this;
    }

    public CatEyeTileTMSSource setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public CatEyeTileTMSSource setTileSize(int tileSize) {
        this.tileSize = tileSize;
        return this;
    }

    public CatEyeTileTMSSource setZoomLevelMax(byte zoomLevelMax) {
        this.zoomLevelMax = zoomLevelMax;
        return this;
    }

    public CatEyeTileTMSSource setZoomLevelMin(byte zoomLevelMin) {
        this.zoomLevelMin = zoomLevelMin;
        return this;
    }
}
