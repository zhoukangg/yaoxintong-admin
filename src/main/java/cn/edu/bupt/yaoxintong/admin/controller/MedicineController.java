package cn.edu.bupt.yaoxintong.admin.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.bupt.yaoxintong.admin.componet.PasswordHelper;
import cn.edu.bupt.yaoxintong.admin.pojo.Medicine;
import cn.edu.bupt.yaoxintong.admin.repository.LoginTokenRepository;
import cn.edu.bupt.yaoxintong.admin.repository.MedicineRepository;
import cn.edu.bupt.yaoxintong.admin.util.Constant;
import cn.edu.bupt.yaoxintong.admin.util.Logger;
import cn.edu.bupt.yaoxintong.admin.util.ReturnModel;
import cn.edu.bupt.yaoxintong.admin.util.StringUtil;

@RestController
@RequestMapping(value = "/medicine")
public class MedicineController {

    private static final Logger logger = Logger.getInstance();

    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    PasswordHelper passwordHelper;

    @Autowired
    LoginTokenRepository loginTokenRepository;

    @RequestMapping(value = "/readAll", method = RequestMethod.POST)
    public ReturnModel readAll(HttpServletResponse response, HttpServletRequest request) {
        System.out.println("zzzy");
        // 解决Ajax跨域请求问题
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");

        ReturnModel result = new ReturnModel();
        result.setResult(true);
        result.setDatum(medicineRepository.findAll());
        return result;

    }

    @RequestMapping(value = "/pagingAndSorting", method = RequestMethod.POST)
    public ReturnModel pagingAndSorting(int page, int size, HttpServletResponse response, HttpServletRequest request) {
        // 解决Ajax跨域请求问题
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        logger.info("======进入了MedicineController的/register方法，参数：page = " + page + " size = " + size);
        Pageable pageable = new PageRequest(page, size);
        ReturnModel result = new ReturnModel();
        result.setResult(true);
        result.setDatum(medicineRepository.findAll(pageable));
        return result;

    }

    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public ReturnModel read(String id, HttpServletResponse response, HttpServletRequest request) {
        // 解决Ajax跨域请求问题
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");

        ReturnModel result = new ReturnModel();
        try {
            if (!StringUtil.isEmpty(id)) {
                Medicine medicine = medicineRepository.findById(id);
                result.setResult(true);
                result.setDatum(medicine);
            } else {
                result.setResult(false);
            }

        } catch (Exception e) {
            // TODO: handle exception
            result.setResult(false);
        }

        return result;
    }

    //同步吉药网的药品数据
    @RequestMapping(value = "/importMedicines", method = RequestMethod.POST)
    public ReturnModel importMedicines(List<Medicine> medicines, HttpServletResponse response, HttpServletRequest request) {

        // 解决Ajax跨域请求问题
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");

        ReturnModel result = new ReturnModel();
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ReturnModel delete(String id, HttpServletResponse response, HttpServletRequest request) {

        // 解决Ajax跨域请求问题
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");

        ReturnModel result = new ReturnModel();
        try {
            if (!StringUtil.isEmpty(id)) {
                Medicine medicine = medicineRepository.findById(id);
                if (medicine != null) {
                    medicineRepository.delete(id);
                    result.setReason("删除成功");
                    result.setResult(true);
                } else {
                    result.setReason("未查找到该用户");
                    result.setResult(false);
                }
            } else {
                result.setResult(false);
            }

        } catch (Exception e) {
            // TODO: handle exception
            result.setResult(false);
        }
        return result;

    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ReturnModel register(
            String drugName,

            String brand,

            String manufacturer,

            String licenseNumber,

            String recommend,

            String drugForm,

            String type,

            String drugType1,

            String drugType2,

            String drugType3,

            String prescriptionType,

            String importType,

            String drugSpecifications,

            String productPlace,

            String validPeriod,

            String useForIllness,

            String useForPeople,

            String useFunction,

            String dosageUse,

            String storageCondition,

            String productWeight,

            Double price,

            Integer hot,
            HttpServletResponse response, HttpServletRequest request) {

        // 解决Ajax跨域请求问题
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        logger.info("======进入了MedicineController的/register方法，参数：drugName = " + drugName + " brand = " + brand
                + "manufacturer = " + manufacturer + "licenseNumber = " + licenseNumber
                + "recommend = " + recommend.toString() + "drugForm = " + drugForm
                + "type = " + type + "drugType1 = " + drugType1
                + "drugType2 = " + drugType2 + "drugType3 = " + drugType3
                + "prescriptionType = " + prescriptionType + "importType = " + importType
                + "drugSpecifications = " + drugSpecifications + "productPlace = " + productPlace
                + "validPeriod = " + validPeriod + "useForIllness = " + useForIllness
                + "useForPeople = " + useForPeople + "useFunction = " + useFunction
                + "dosageUse = " + dosageUse + "storageCondition = " + storageCondition
                + "productWeight = " + productWeight + "price = " + price.toString()
                + "hot = " + hot.toString());
        ReturnModel result = new ReturnModel();
//		if(
//				StringUtil.isEmpty(drugName) || StringUtil.isEmpty(brand)
//				|| StringUtil.isEmpty(manufacturer) || StringUtil.isEmpty(licenseNumber)
//				|| StringUtil.isEmpty(recommend.toString()) || StringUtil.isEmpty(drugForm)
//				|| StringUtil.isEmpty(type) || StringUtil.isEmpty(drugType1)
//				|| StringUtil.isEmpty(drugType2) || StringUtil.isEmpty(drugType3) 
//				|| StringUtil.isEmpty(prescriptionType) || StringUtil.isEmpty(importType)
//				|| StringUtil.isEmpty(drugSpecifications) || StringUtil.isEmpty(productPlace)
//				|| StringUtil.isEmpty(validPeriod) || StringUtil.isEmpty(useForIllness)
//				|| StringUtil.isEmpty(useForPeople) || StringUtil.isEmpty(useFunction)
//				|| StringUtil.isEmpty(dosageUse) || StringUtil.isEmpty(storageCondition)
//				|| StringUtil.isEmpty(productWeight) || StringUtil.isEmpty(price.toString())
//				|| StringUtil.isEmpty(hot.toString())) {
//			result.setResult(false);
//			result.setReason(Constant.REASON_UNKNOW);
//			return result;
//		}
//		YaoxintongBusiness medicine = medicineRepository.findById(id);
//		// case already registered
//		if (business != null) {
//			result.setResult(false);
//			result.setReason(Constant.REASON_USERNAME_IS_EXIST);
//			return result;
//		}

        // 设置药品信息
        Medicine medicine = new Medicine();
        String id = UUID.randomUUID().toString();
        medicine.setId(id);
        medicine.setDrugName(drugName);
        medicine.setBrand(brand);
        medicine.setManufacturer(manufacturer);
        medicine.setLicenseNumber(licenseNumber);
        medicine.setRecommend(recommend);
        medicine.setDrugForm(drugForm);
        medicine.setType(type);
        medicine.setDrugType1(drugType1);
        medicine.setDrugType2(drugType2);
        medicine.setDrugType3(drugType3);
        medicine.setPrescriptionType(prescriptionType);
        medicine.setImportType(importType);
        medicine.setDrugSpecifications(drugSpecifications);
        medicine.setProductPlace(productPlace);
        medicine.setValidPeriod(validPeriod);

        medicine.setUseForIllness(useForIllness);
        medicine.setUseForPeople(useForPeople);

        medicine.setUseFunction(useFunction);
        medicine.setDosageUse(dosageUse);
        medicine.setStorageCondition(storageCondition);
        medicine.setProductWeight(productWeight);
        medicine.setPrice(price);
        medicine.setHot(hot);
        medicine.setAuthentication(0);

        try {
            medicineRepository.save(medicine);
            result.setDatum(medicine);
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
        }
        return result;
    }


    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ReturnModel modify(
            String id,

            String drugName,

            String brand,

            String manufacturer,

            String licenseNumber,

            String recommend,

            String drugForm,

            String type,

            String drugType1,

            String drugType2,

            String drugType3,

            String prescriptionType,

            String importType,

            String drugSpecifications,

            String productPlace,

            String validPeriod,

            String useForIllness,

            String useForPeople,

            String useFunction,

            String dosageUse,

            String storageCondition,

            String productWeight,

            Double price,

            Integer hot,
            HttpServletResponse response, HttpServletRequest request) {

        // 解决Ajax跨域请求问题
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        ReturnModel result = new ReturnModel();
//		if(
//				StringUtil.isEmpty(id) || 
//				StringUtil.isEmpty(drugName) || StringUtil.isEmpty(brand)
//				|| StringUtil.isEmpty(manufacturer) || StringUtil.isEmpty(licenseNumber)
//				|| StringUtil.isEmpty(recommend.toString()) || StringUtil.isEmpty(drugForm)
//				|| StringUtil.isEmpty(type) || StringUtil.isEmpty(drugType1)
//				|| StringUtil.isEmpty(drugType2) || StringUtil.isEmpty(drugType3) 
//				|| StringUtil.isEmpty(prescriptionType) || StringUtil.isEmpty(importType)
//				|| StringUtil.isEmpty(drugSpecifications) || StringUtil.isEmpty(productPlace)
//				|| StringUtil.isEmpty(validPeriod) || StringUtil.isEmpty(useForIllness)
//				|| StringUtil.isEmpty(useForPeople) || StringUtil.isEmpty(useFunction)
//				|| StringUtil.isEmpty(dosageUse) || StringUtil.isEmpty(storageCondition)
//				|| StringUtil.isEmpty(productWeight) || StringUtil.isEmpty(price.toString())
//				|| StringUtil.isEmpty(hot.toString())) {
//			result.setResult(false);
//			result.setReason(Constant.REASON_UNKNOW);
//			return result;
//		}

        Medicine medicine = medicineRepository.findById(id);
        // case not registered
        if (medicine == null) {
            result.setResult(false);
            result.setReason("药品不存在");
            return result;
        }

        // 设置药品信息
        //if(!drugName.isEmpty())
        medicine.setDrugName(drugName);
        //if(!brand.isEmpty())
        medicine.setBrand(brand);
        //if(!manufacturer.isEmpty())
        medicine.setManufacturer(manufacturer);
        //if(!licenseNumber.isEmpty())
        medicine.setLicenseNumber(licenseNumber);
        //if(!recommend.isEmpty())
        medicine.setRecommend(recommend);
        //if(!drugForm.isEmpty())
        medicine.setDrugForm(drugForm);
        //if(!type.isEmpty())
        medicine.setType(type);
        //if(!drugType1.isEmpty())
        medicine.setDrugType1(drugType1);
        //if(!drugType2.isEmpty())
        medicine.setDrugType2(drugType2);
        //if(!drugType3.isEmpty())
        medicine.setDrugType3(drugType3);
        //if(!prescriptionType.isEmpty())
        medicine.setPrescriptionType(prescriptionType);
        //if(!importType.isEmpty())
        medicine.setImportType(importType);
        //if(!drugSpecifications.isEmpty())
        medicine.setDrugSpecifications(drugSpecifications);
        //if(!productPlace.isEmpty())
        medicine.setProductPlace(productPlace);
        //if(!validPeriod.isEmpty())
        medicine.setValidPeriod(validPeriod);
        //if(!useForIllness.isEmpty())
        medicine.setUseForIllness(useForIllness);
        //if(!useForPeople.isEmpty())
        medicine.setUseForPeople(useForPeople);
        //if(!useFunction.isEmpty())
        medicine.setUseFunction(useFunction);
        //if(!dosageUse.isEmpty())
        medicine.setDosageUse(dosageUse);
        //if(!storageCondition.isEmpty())
        medicine.setStorageCondition(storageCondition);
        //if(!productWeight.isEmpty())
        medicine.setProductWeight(productWeight);
        //if(!price.toString().isEmpty())
        medicine.setPrice(price);

        //if(!hot.toString().isEmpty())
        medicine.setHot(hot);

        try {
            medicineRepository.save(medicine);
            result.setDatum(medicine);
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
        }
        return result;
    }


    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public ReturnModel authenticationMedicine(String id, HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        ReturnModel returnModel = new ReturnModel();
        try {
            if (medicineRepository.authentication(id) == 1) {
                returnModel.setResult(true);
            } else {
                returnModel.setResult(false);
            }
        } catch (Exception e) {
            returnModel.setResult(false);
        }

        return returnModel;
    }
}
