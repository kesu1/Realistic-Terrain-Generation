package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPAlps extends RTGBiomeBOP {

    public RTGBiomeBOPAlps() {

        super(BOPBiomes.alps.get(), Biomes.FROZEN_RIVER);

        this.noLakes = true;
    }

    @Override
    public void initConfig() {

        config.GENERATE_EMERALDS.setDefault(true);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBase(120f) {

            // the BoP version has steep slopes and a flat area on top. The RTG version will
            private float start = 0f;// this puts a minimum on "ruggedness" on the top. We want to allow flats
            private float height = 40f; // sets the variability range
            private float width = 80f; // width of irregularity noise on top. We want low, for a lot of features.

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {

                return terrainHighland(x, y, rtgWorld.simplex, rtgWorld.cell, river, start, width, height, base - 62f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {

        return SurfaceBase.surfaceTaiga(this);
    }

    @Override
    public void initDecos() {

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
