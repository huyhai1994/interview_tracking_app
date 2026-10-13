package com.homelab.interview_tracking_app.support;

import com.homelab.interview_tracking_app.company.share.entities.Company;

import java.util.List;

public final class MockCompany {

    private MockCompany() {
    }

    public static Company create(
            String name,
            String taxCode,
            String address,
            String website
    ) {
        Company company = new Company();
        company.setName(name);
        company.setTaxCode(taxCode);
        company.setAddress(address);
        company.setWebsite(website);
        return company;
    }

    public static Company samsung() {
        return create(
                "Samsung Electronics Vietnam",
                "2300123456",
                "Bac Ninh, Vietnam",
                "https://www.samsung.com"
        );
    }

    public static Company lg() {
        return create(
                "LG Electronics Vietnam",
                "0200123456",
                "Hai Phong, Vietnam",
                "https://www.lg.com"
        );
    }

    public static Company naver() {
        return create(
                "Naver Vietnam",
                "0100123456",
                "Hanoi, Vietnam",
                "https://www.naver.com"
        );
    }

    public static Company saltlux() {
        return create(
                "Saltlux Vietnam",
                "0100987654",
                "Hanoi, Vietnam",
                "https://www.saltlux.com"
        );
    }

    public static Company fpt() {
        return create(
                "FPT Software",
                "0100765432",
                "Hanoi, Vietnam",
                "https://fptsoftware.com"
        );
    }

    public static List<Company> all() {
        return List.of(
                samsung(),
                lg(),
                naver(),
                saltlux(),
                fpt()
        );
    }
}