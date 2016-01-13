package com.lego.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sargis on 5/1/15.
 */
public class LegoResolutionDescriptor implements ResolutionDescriptor {
    private static final String TAG = LegoResolutionDescriptor.class.getCanonicalName();
    private final Array<Fraction> supportedAspectRatios;
    private final Map<String, Array<String>> supportedAspectRatiosToResolutionsMap;
    private final Map<String, String> aliases;
    private int originWith;
    private int originHeight;
    private Fraction aspectRatio;
    private int with;
    private int height;

    public LegoResolutionDescriptor() {
        supportedAspectRatios = new Array<>();
        supportedAspectRatiosToResolutionsMap = new HashMap<>();
        aliases = new HashMap<>();
    }

    @Override
    public void init() {
        supportedAspectRatios.clear();
        supportedAspectRatiosToResolutionsMap.clear();
        aliases.clear();
        FileHandle resolutionDescriptorXmlFileHandle = Gdx.files.internal("resolution-descriptor.xml");
        XmlReader xmlReader = new XmlReader();
        try {
            XmlReader.Element resolutionDescriptorXml = xmlReader.parse(resolutionDescriptorXmlFileHandle);
            Array<XmlReader.Element> aspectRatios = resolutionDescriptorXml.getChildrenByName("aspect-ratio");
            for (XmlReader.Element aspectRatio : aspectRatios) {
                String aspectRatioValue = aspectRatio.getAttribute("value");
                String[] valueAsInts = aspectRatioValue.split("\\|");
                supportedAspectRatios.add(new Fraction(Integer.valueOf(valueAsInts[0]), Integer.valueOf(valueAsInts[1])));
                Array<XmlReader.Element> resolutions = aspectRatio.getChildrenByName("resolution");
                Array<String> resolutionsArray = new Array<String>();
                for (XmlReader.Element resolution : resolutions) {
                    String resolutionValue = resolution.getAttribute("value");
                    resolutionsArray.add(resolutionValue);
                }
                supportedAspectRatiosToResolutionsMap.put(aspectRatioValue, resolutionsArray);
                String aspectRatioAlias = aspectRatio.getAttribute("alias", aspectRatioValue);
                if (!aspectRatioAlias.equals(aspectRatioValue)) {
                    aliases.put(aspectRatioAlias, aspectRatioValue);
                }
            }
            with = originWith = Gdx.graphics.getWidth();
            height = originHeight = Gdx.graphics.getHeight();
            aspectRatio = new Fraction(originWith, originHeight);
            if (!isAspectRatioSupported(aspectRatio)) {
                aspectRatio = getNearestAspectRatio();
            }
            calculateSupportedScreenSizes();
            Gdx.app.log(TAG, "aspect ratio : " + aspectRatio.getCanonical() + ", originWith : " + originWith + " originHeight : " + originHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateSupportedScreenSizes() {
        Array<String> resolutions = supportedAspectRatiosToResolutionsMap.get(aspectRatio.getCanonical());
        int min = Integer.MAX_VALUE;
        int tempWidth = 0;
        int tempHeight = 0;
        for (String resolution : resolutions) {
            String[] split = resolution.split("x");
            int newWidth = Integer.parseInt(split[0]);
            int newHeight = Integer.parseInt(split[1]);
            int dMin = Math.abs(originWith - newWidth);
            if (min > dMin) {
                min = dMin;
                tempWidth = newWidth;
                tempHeight = newHeight;
            }
        }
        with = tempWidth;
        height = tempHeight;
    }

    public int getOriginWith() {
        return originWith;
    }

    public int getOriginHeight() {
        return originHeight;
    }

    public Fraction getAspectRatio() {
        return aspectRatio;
    }

    private boolean isAspectRatioSupported(Fraction aspectRatio) {
        for (Fraction fraction : supportedAspectRatios) {
            if (fraction.equals(aspectRatio)) {
                return true;
            }
        }
        return false;
    }

    public Fraction getNearestAspectRatio() {
        if (aliases.containsKey(aspectRatio.getCanonical())) {
            String aspectRatioAlias = aliases.get(aspectRatio.getCanonical());
            for (Fraction supportedAspectRatio : supportedAspectRatios) {
                if (aspectRatioAlias.equals(supportedAspectRatio.getCanonical())) {
                    return supportedAspectRatio;
                }
            }
        }
        Fraction nearestAspectRatio = null;
        double min = Double.MAX_VALUE;
        for (Fraction supportedAspectRatio : supportedAspectRatios) {
            double dValue = Math.abs(supportedAspectRatio.doubleValue() - aspectRatio.doubleValue());
            if (min > dValue) {
                nearestAspectRatio = supportedAspectRatio;
                min = dValue;
            }
        }
        return nearestAspectRatio;
    }

    public int getHeight() {
        return height;
    }

    public int getWith() {
        return with;
    }

    public String getResolution() {
        return with + "x" + height;
    }
}
