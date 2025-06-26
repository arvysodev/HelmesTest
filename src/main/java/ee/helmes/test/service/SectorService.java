package ee.helmes.test.service;

import ee.helmes.test.dto.SectorView;
import ee.helmes.test.entity.Sector;
import ee.helmes.test.repository.SectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class SectorService {

    private final SectorRepository sectorRepository;

    public List<SectorView> getSectorsForView() {
        List<Sector> allSectors = sectorRepository.findAll();
        List<SectorView> sectorViews = new ArrayList<>();
        List<Sector> rootSectors = allSectors.stream()
                .filter(s -> s.getParent() == null)
                .sorted(Comparator.comparing(Sector::getName))
                .toList();

        for (Sector root : rootSectors) {
            addChildrenRecursively(root, 0, sectorViews);
        }

        return sectorViews;
    }

    private void addChildrenRecursively(Sector sector, int depth, List<SectorView> sectorViews) {
        sectorViews.add(new SectorView(sector.getId(), sector.getName(), depth));
        sector.getChildren().stream()
                .sorted(Comparator.comparing(Sector::getName))
                .forEach(child -> addChildrenRecursively(child, depth + 1, sectorViews));
    }
}
